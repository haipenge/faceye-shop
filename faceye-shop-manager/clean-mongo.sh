#shop db clean script 
#Write by haipenge 2015.06.18
dir=/tools/mongodb/mongodb-osx-x86_64-3.0.1
db=shop
cd $dir
./bin/mongo;
use shop;
db.customer_customer.remove({},{multi:true});
db.inventory_inventory.remove({},{multi:true});
db.inventory_invoice.remove({},{multi:true});
db.inventory_invoice_item.remove({},{multi:true});
db.order_order.remove({},{multi:true});
db.order_item.remove({},{multi:true});
db.order_cart.remove({},{multi:true});
db.order_cart_item.remove({},{multi:true});
db.customer_customer.remove({},{multi:true});
db.customer_address.remove({},{multi:true});
db.product_category.remove({},{multi:true});
db.product_product.remove({},{multi:true});
db.product_dynamic_property.remove({},{multi:true});
db.product_dynamic_property_value.remove({},{multi:true});
db.product_product_property.remove({},{multi:true});
db.product_product_sku.remove({},{multi:true});
db.product_sku_property.remove({},{multi:true});
db.product_image.remove({},{multi:true});
exit
