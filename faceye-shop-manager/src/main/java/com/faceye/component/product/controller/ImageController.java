package com.faceye.component.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.faceye.component.product.entity.Image;
import com.faceye.component.product.entity.Product;
import com.faceye.component.product.service.ImageService;
import com.faceye.component.setting.controller.BaseShopController;
import com.faceye.feature.upload.Upload;
import com.faceye.feature.upload.UploadResult;
import com.faceye.feature.util.AjaxResult;
import com.faceye.feature.util.http.HttpUtil;
import com.faceye.feature.util.view.MessageBuilder;

/**
 * 模块:product<br>
 * 实体:Image<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年12月10日<br>
 */
@Controller
@Scope("prototype")
@RequestMapping("/product/image")
public class ImageController extends BaseShopController<Image, Long, ImageService> {

	@Autowired
	public ImageController(ImageService service) {
		super(service);
	}

	/**
	 * 首页<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {
		Map searchParams = HttpUtil.getRequestParams(request);
		Page<Image> page = this.service.getPage(searchParams, getPage(searchParams), getSize(searchParams));
		model.addAttribute("page", page);
		resetSearchParams(searchParams);
		model.addAttribute("searchParams", searchParams);
		return "product.image.manager";
	}

	/**
	 * 转向编辑或新增页面<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
		if (id != null) {
			beforeInput(model, request);
			Image entity = this.service.get(id);
			model.addAttribute("image", entity);
		}
		return "product.image.update";
	}

	/**
	 * 转向新增页面<br>
	 * @todo<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月27日<br>
	 */
	@RequestMapping(value = "/input")
	public String input(Image image, Model model, HttpServletRequest request) {
		beforeInput(model, request);
		return "product.image.update";
	}

	/**
	 * 数据保存<br>
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public String save(@RequestParam(required = true) Long productId, @RequestParam(required = true) String filename, Model model,
			HttpServletRequest request) {
		Image image = this.service.getImageByFilename(filename);
		if (image == null) {
			Product product = this.productService.get(productId);
			image = new Image();
			image.setFilename(filename);
			image.setProduct(product);
			image.setShop(product.getShop());
			List<Image> images = this.service.getImageByProductId(productId);
			if (CollectionUtils.isEmpty(images)) {
				image.setIsDefault(true);
			}
			this.service.save(image);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 取得产品图片
	 * @todo
	 * @param productId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月20日
	 */
	@RequestMapping("/getImagesByProduct")
	@ResponseBody
	public String getImagesByProduct(@RequestParam(required = true) Long productId) {
		List<Image> images = this.service.getImageByProductId(productId);
		List<UploadResult> uploadResults = new ArrayList<UploadResult>(0);
		if (CollectionUtils.isNotEmpty(images)) {
			for (Image image : images) {
				UploadResult uploadResult = new UploadResult();
				uploadResult.setGenerateFileName(image.getFilename());
				uploadResult.setPath(image.getPath());
				uploadResult.setSize("5");
				uploadResults.add(uploadResult);
			}
		}
		return Upload.uploadResults2Json(uploadResults);
	}

	/**
	 * 设置默认图片
	 * @todo
	 * @param imageId
	 * @param productId
	 * @return
	 * @author:@haipenge
	 * 联系:haipenge@gmail.com
	 * 创建时间:2015年6月20日
	 */
	@RequestMapping("/setDefaultImage")
	@ResponseBody
	public String setDefaultImage(@RequestParam(required=false) Long imageId, @RequestParam(required=false) Long productId, @RequestParam(required=false) String filename) {
		Product product = null;
		Image image = null;
		if (StringUtils.isNotEmpty(filename)) {
			image = this.service.getImageByFilename(filename);
			if (image != null) {
				product = image.getProduct();
			}
		} else {
			product = this.productService.get(productId);
			image = this.service.get(imageId);
		}
		if (product != null && image != null) {
			List<Image> images = this.service.getImageByProductId(product.getId());
			for (Image img : images) {
				img.setIsDefault(false);
				this.service.save(img);
			}
			image.setIsDefault(true);
			this.service.save(image);
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
	}

	/**
	 * 数据删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id, @RequestParam String filename, RedirectAttributes redirectAttributes,
			RedirectAttributesModelMap model) {
		if (id != null) {
			Image image = null;
			if (id == 0) {
				image = this.service.getImageByFilename(filename);
			} else {
				image = this.service.get(id);
			}
			if (image != null) {
				if (beforeRemove(image, model)) {
					this.service.remove(image);
					// MessageBuilder.getInstance().setMessage(model,image+" "+ this.getI18N("global.remove.success"));
				}
			}
		}
		return AjaxResult.getInstance().buildDefaultResult(true);
		// return "redirect:/product/image/home";
	}

	/**
	 * 数据批量删除<br>
	 * 
	 * @todo<br>
	 * @return<br>
	 * @author:@haipenge haipenge@gmail.com 2014年5月24日<br>
	 */
	@RequestMapping("/multiRemove")
	@ResponseBody
	public String remove(@RequestParam(required = true) String ids, RedirectAttributes redirectAttributes, Model model) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				Image image = this.service.get(Long.parseLong(id));
				if (image != null) {
					if (beforeRemove(image, model)) {
						this.service.remove(image);
						// MessageBuilder.getInstance().setMessage(model,image+" "+ this.getI18N("global.remove.success"));
					}
				}
			}
		}
		String messages = MessageBuilder.getInstance().getMessages(model);
		return AjaxResult.getInstance().buildDefaultResult(StringUtils.isEmpty(messages), messages);
	}

	/**
	 * 取得数据明细<br>
	 * @todo<br>
	 * @param id<br>
	 * @param model<br>
	 * @return<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2014年5月26日<br>
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		if (id != null) {
			Image image = this.service.get(id);
			model.addAttribute("image", image);
		}
		return "product.image.detail";
	}

	// /////////////////////////////////////////////以下为回调函数////////////////////////////////////////////
	/**
	 * 新增、编辑页面的前置逻辑处理<br>
	 * @todo<br>
	 * @param model<br>
	 * @param request<br>
	 * @author:@haipenge<br>
	 * haipenge@gmail.com<br>
	 * 2015年4月5日<br>
	 */
	protected void beforeInput(Model model, HttpServletRequest request) {

	}

	/**
	 *
	 *保存数据前的回调函数
	 */
	protected void beforeSave(Image image, HttpServletRequest request) {

	}

	/**
	 * 删除前 数据回调
	 */
	protected boolean beforeRemove(Image image, Model model) {
		boolean res = true;

		return res;
	}

}
