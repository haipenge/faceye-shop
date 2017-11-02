package com.faceye.component.lbs.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.faceye.component.lbs.entity.GeoLibrary;
import com.faceye.component.lbs.repository.mongo.GeoLibraryRepository;
import com.faceye.component.lbs.service.GeoLibraryService;
import com.faceye.component.lbs.service.model.Area;
import com.faceye.component.lbs.service.model.City;
import com.faceye.component.lbs.service.model.Province;
import com.faceye.feature.repository.mongo.DynamicSpecifications;
import com.faceye.feature.service.impl.BaseMongoServiceImpl;
import com.faceye.feature.util.Json;
import com.fasterxml.jackson.core.type.TypeReference;
import com.querydsl.core.types.Predicate;
/**
 * GeoLibrary 服务实现类<br>
 * @author @haipenge <br>
 * haipenge@gmail.com<br>
*  Create Date:2014年5月20日<br>
 */

@Service
public class GeoLibraryServiceImpl extends BaseMongoServiceImpl<GeoLibrary, Long, GeoLibraryRepository> implements GeoLibraryService {

	@Value("classpath:/geo/province.json")
	private Resource provinceResource = null;
	@Value("classpath:/geo/city.json")
	private Resource cityResource = null;
	@Value("classpath:/geo/area.json")
	private Resource areaResource = null;

	@Autowired
	public GeoLibraryServiceImpl(GeoLibraryRepository dao) {
		super(dao);
	}

	@Override
	public Page<GeoLibrary> getPage(Map<String, Object> searchParams, int page, int size) {
		if (page != 0) {
			page = page - 1;
		}
		// SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
		// EntityPath<GeoLibrary> entityPath = resolver.createPath(entityClass);
		// PathBuilder<GeoLibrary> builder = new PathBuilder<GeoLibrary>(entityPath.getType(),
		// entityPath.getMetadata());
		// Path path = entityPath.getRoot();
		// List<Predicate> predicates=DynamicSpecifications.buildPredicates(searchParams, entityClass);
		// Predicate predicate=DynamicSpecifications.builder(predicates);
		// NumberPath numberPath = new NumberPath(Number.class, path, "age");
		// predicates.add(numberPath.eq(15));
		Predicate predicate = DynamicSpecifications.builder(searchParams, entityClass);
		if (predicate != null) {
			logger.debug(">>FaceYe -->Query predicate is:" + predicate.toString());
		}
		Sort sort = new Sort(Direction.DESC, "id");
		Page<GeoLibrary> res = null;
		if (size != 0) {
			Pageable pageable = new PageRequest(page, size, sort);
			res = this.dao.findAll(predicate, pageable);
		} else {
			// OrderSpecifier<Comparable> orderPOrderSpecifier=new OrderSpecifier<Comparable>(new Order(), new
			// NumberExpression<GeoLibrary>("id") {
			// })
			List<GeoLibrary> items = (List) this.dao.findAll(predicate);
			res = new PageImpl<GeoLibrary>(items);

		}
		return res;
	}

	@Override
	public void initGeoLibrary() {
		String[] keys = new String[] { "province", "city", "area" };
		for (String key : keys) {
			String source = this.load(key);
			if (StringUtils.isNotEmpty(key)) {
				this.initSource(key, source);
			}
		}
	}

	/**
	 * 初始化资源
	 * 
	 * @param key
	 * @param source
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午7:12:18
	 */
	private void initSource(String key, String source) {
		if (StringUtils.equalsIgnoreCase(key, "province")) {
			this.initProvince(source);
		} else if (StringUtils.equalsIgnoreCase(key, "area")) {
			this.initArea(source);
		} else if (StringUtils.equalsIgnoreCase(key, "city")) {
			this.initCity(source);
		}
	}

	/**
	 * 初省化省份数据
	 * 
	 * @param source
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午7:18:22
	 */
	private void initProvince(String source) {
		TypeReference<List<Province>> typeReference = new TypeReference<List<Province>>() {
		};
		List<Province> provinces = Json.toObject(source, typeReference);
		if (CollectionUtils.isNotEmpty(provinces)) {
			for (Province province : provinces) {
				GeoLibrary geoLibrary = new GeoLibrary();
				geoLibrary.setLevel(0);
				geoLibrary.setName(province.getName());
				geoLibrary.setOrderIndex(province.getProSort());
				geoLibrary.setGeoId(province.getProId().longValue());
				geoLibrary.setParentId(0L);
				this.save(geoLibrary);
			}
		}
	}

	/**
	 * 初始化城市数据
	 * 
	 * @param source
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午7:19:17
	 */
	private void initCity(String source) {
		TypeReference<List<City>> typeReference = new TypeReference<List<City>>() {
		};
		List<City> cities = Json.toObject(source, typeReference);
		if (CollectionUtils.isNotEmpty(cities)) {
			for (City city : cities) {
				GeoLibrary geoLibrary = new GeoLibrary();
				geoLibrary.setLevel(1);
				geoLibrary.setName(city.getName());
				geoLibrary.setOrderIndex(city.getCitySort());
				geoLibrary.setGeoId(city.getCityId().longValue());
				GeoLibrary parent = this.dao.getGeoLibraryByGeoIdAndLevel(city.getProId().longValue(), 0);
				if (parent != null) {
					geoLibrary.setParentId(parent.getId());
				} else {
					logger.error(">>FaceYe city :" + city.getName() + " parent id is null<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				this.save(geoLibrary);
			}
		}
	}

	/**
	 * 初始化地区数据
	 * 
	 * @param source
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午7:23:39
	 */
	private void initArea(String source) {
		TypeReference<List<Area>> typeReference = new TypeReference<List<Area>>() {
		};
		List<Area> areas = Json.toObject(source, typeReference);
		if (CollectionUtils.isNotEmpty(areas)) {
			for (Area area : areas) {
				GeoLibrary geoLibrary = new GeoLibrary();
				geoLibrary.setGeoId(area.getId().longValue());
				geoLibrary.setLevel(2);
				geoLibrary.setName(area.getDisName());
				geoLibrary.setOrderIndex(area.getDisSort());
				GeoLibrary parent = this.dao.getGeoLibraryByGeoIdAndLevel(area.getCityId().longValue(), 1);
				if (parent != null) {
					geoLibrary.setParentId(parent.getId());
				} else {
					logger.error(">>FaceYe area :" + area.getDisName() + " parent id is null <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				}
				this.save(geoLibrary);
			}
		}
	}

	/**
	 * 加载资源文件
	 * 
	 * @param key
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月23日 下午4:20:50
	 */
	private String load(String key) {
		String res = "";
		byte[] buffer = new byte[409600];
		if (StringUtils.equalsIgnoreCase(key, "province")) {
			try {
				IOUtils.read(new FileInputStream(provinceResource.getFile()), buffer);
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		} else if (StringUtils.equalsIgnoreCase(key, "city")) {
			try {
				IOUtils.read(new FileInputStream(cityResource.getFile()), buffer);
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		} else {
			try {
				IOUtils.read(new FileInputStream(areaResource.getFile()), buffer);
			} catch (IOException e) {
				logger.error(">>FaceYe Throws Exception:", e);
			}
		}
		res = new String(buffer);
		return res;
	}

	/**
	 * 取得GEO 级联信息
	 */
	private  void getFullGeoLibraries(List<GeoLibrary> geoLibraries, Long id) {
		if (null == geoLibraries) {
			geoLibraries = new ArrayList<GeoLibrary>();
		}
		GeoLibrary geoLibrary = this.get(id);
		if (geoLibrary != null) {
			geoLibraries.add(geoLibrary);
			if (geoLibrary.getParentId().compareTo(0L) > 0) {
				getFullGeoLibraries(geoLibraries, geoLibrary.getParentId());
			}
		}
	}

	/**
	 * 
	 * @param geoLibraries
	 * @return
	 * @Desc:
	 * @Author:haipenge
	 * @Date:2015年12月29日 下午3:54:03
	 */
	private List<GeoLibrary> order(List<GeoLibrary> geoLibraries) {
		List<GeoLibrary> result = new ArrayList<GeoLibrary>();
		for (int i = geoLibraries.size() - 1; i >= 0; i--) {
			result.add(geoLibraries.get(i));
		}
		return result;
	}

	@Override
	public List<GeoLibrary> getGeoLibrariesDetail(Long id) {
		List<GeoLibrary> result = new ArrayList<GeoLibrary>();
		this.getFullGeoLibraries(result, id);
		List<GeoLibrary> geoLibraries=this.order(result);
		return geoLibraries;
	}

	@Override
	public String getGeoLibrariesShowDetail(Long id) {
		String res="";
		List<GeoLibrary> geoLibraries=getGeoLibrariesDetail(id);
		if(CollectionUtils.isNotEmpty(geoLibraries)){
			StringBuilder sb=new StringBuilder();
			for(GeoLibrary geoLibrary:geoLibraries){
				sb.append(geoLibrary.getName());
				sb.append(" ");
			}
			res=sb.toString();
		}
		return res;
	}
}/** @generate-service-source@ **/
