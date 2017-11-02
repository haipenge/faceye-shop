package com.faceye.component.product.repository.mongo;

import java.util.List;

import com.faceye.component.product.entity.Image;
import com.faceye.feature.repository.mongo.BaseMongoRepository;
/**
 * Image 实体DAO<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */
public interface ImageRepository extends BaseMongoRepository<Image,Long> {
	
	public Image getImageByFilename(String filename);
	
	public List<Image> getImageByProductId(Long productId);
}/**@generate-repository-source@**/
