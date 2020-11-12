package com.mhyl.performance.appraisal.http;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mhyl.performance.appraisal.utils.BeanMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ArchieDing
 * @since 2020/09/01
 */
@Data
public class PageVO<T> implements Serializable {
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Long current;
	/**
	 * 总页数
	 */
	@ApiModelProperty(value = "总页数")
	private Long pages;
	/**
	 * 每页大小
	 */
	@ApiModelProperty(value = "每页大小")
	private Long size;
	/**
	 * 总记录数
	 */
	@ApiModelProperty(value = "总记录数")
	private Long total;
	/**
	 * 当前页记录
	 */
	@ApiModelProperty(value = "当前页记录")
	private List<T> list;

	public static <S, T> PageVO<T> convert(IPage<S> sPage, Class<S> sClass, Class<T> tClass) {
		PageVO<T> tPage = new PageVO<>();
		tPage.setCurrent(sPage.getCurrent());
		tPage.setPages(sPage.getPages());
		tPage.setSize(sPage.getSize());
		tPage.setTotal(sPage.getTotal());
		tPage.setList(BeanMapper.mapList(sPage.getRecords(), sClass, tClass));
		return tPage;
	}

}
