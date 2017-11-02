package com.faceye.component.setting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.customer.service.AddressService;
import com.faceye.component.customer.service.CustomerService;
import com.faceye.component.inventory.service.InventoryService;
import com.faceye.component.inventory.service.InvoiceItemService;
import com.faceye.component.inventory.service.InvoiceService;
import com.faceye.component.order.service.CartItemService;
import com.faceye.component.order.service.CartService;
import com.faceye.component.order.service.ItemService;
import com.faceye.component.order.service.OrderService;
import com.faceye.component.product.entity.Category;
import com.faceye.component.product.entity.DynamicProperty;
import com.faceye.component.product.service.CategoryService;
import com.faceye.component.product.service.DynamicPropertyService;
import com.faceye.component.product.service.DynamicPropertyValueService;
import com.faceye.component.product.service.ProductPropertyService;
import com.faceye.component.product.service.ProductService;
import com.faceye.component.product.service.ProductSkuService;
import com.faceye.component.product.service.SkuPropertyService;
import com.faceye.component.setting.service.BuildTools;

@Service
public class BuildToolsImpl implements BuildTools {
	@Autowired
    private InvoiceItemService invoiceItemService;
	@Autowired
    private InvoiceService invoiceService;
	@Autowired
    private InventoryService inventoryService;
	@Autowired
    private CustomerService customerService;
	@Autowired
    private DynamicPropertyService dynamicPropertyService;
	@Autowired
    private DynamicPropertyValueService dynamicPropertyValueService;
	@Autowired
    private ProductPropertyService productPropertyService;
	@Autowired
    private SkuPropertyService skuPropertyService;
	@Autowired
    private ProductSkuService productSkuService;
	@Autowired
    private ProductService productService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private CartService cartService;
	@Autowired
    private CartItemService cartItemService;
	@Autowired
    private ItemService itemService;
	@Autowired
    private OrderService orderService;
	@Autowired
    private AddressService addressService=null;
    
	@Override
	public void init() {
		this.clear();
	}
	
	private void clear(){
		this.inventoryService.removeAllInBatch();
		this.invoiceItemService.removeAllInBatch();
		this.invoiceService.removeAllInBatch();
		this.customerService.removeAllInBatch();
		this.dynamicPropertyService.removeAllInBatch();
		this.dynamicPropertyValueService.removeAllInBatch();
		this.productPropertyService.removeAllInBatch();
		this.skuPropertyService.removeAllInBatch();
		this.productSkuService.removeAllInBatch();
		this.productService.removeAllInBatch();
		this.categoryService.removeAllInBatch();
		this.cartService.removeAllInBatch();
		this.cartItemService.removeAllInBatch();
		this.itemService.removeAllInBatch();
		this.orderService.removeAllInBatch();
		this.addressService.removeAllInBatch();
	}

}
