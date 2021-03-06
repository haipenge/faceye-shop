package com.faceye.component.product.service;

import java.util.List;

import com.faceye.component.product.entity.Image;
import com.faceye.feature.service.BaseService;
/**
 * Image 服务接品<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ImageService extends BaseService<Image,Long>{

	public Image getImageByFilename(String filename);
	
	public List<Image> getImageByProductId(Long productId);
}/**@generate-service-source@**/
