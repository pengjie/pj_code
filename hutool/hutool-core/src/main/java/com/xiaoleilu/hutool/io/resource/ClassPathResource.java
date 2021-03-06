package com.xiaoleilu.hutool.io.resource;

import java.net.URL;

import com.xiaoleilu.hutool.io.IORuntimeException;
import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ClassUtil;

/**
 * ClassPath资源访问类
 * @author Looly
 *
 */
public class ClassPathResource extends UrlResource{
	
	private String path;
	private ClassLoader classLoader;
	private Class<?> clazz;
	
	//-------------------------------------------------------------------------------------- Constructor start
	/**
	 * 构造
	 * @param path 相对于ClassPath的路径
	 */
	public ClassPathResource(String path) {
		this(path, null, null);
	}

	/**
	 * 构造
	 * @param path 相对于ClassPath的路径
	 * @param classLoader {@link ClassLoader}
	 */
	public ClassPathResource(String path, ClassLoader classLoader) {
		this(path, classLoader, null);
	}

	/**
	 * 构造
	 * @param path 相对于给定Class的路径
	 * @param clazz {@link Class} 用于定位路径
	 */
	public ClassPathResource(String path, Class<?> clazz) {
		this(path, null, clazz);
	}

	/**
	 * 构造
	 * @param path 相对路劲
	 * @param classLoader {@link ClassLoader}
	 * @param clazz {@link Class} 用于定位路径
	 */
	public ClassPathResource(String path, ClassLoader classLoader, Class<?> clazz) {
		super((URL)null);
		Assert.notNull(path, "Path must not be null");
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : ClassUtil.getClassLoader());
		this.clazz = clazz;
		initUrl();
	}
	//-------------------------------------------------------------------------------------- Constructor end
	
	/**
	 * 获得Path
	 * @return path
	 */
	public final String getPath() {
		return this.path;
	}
	
	/**
	 * 获得 {@link ClassLoader}
	 * @return {@link ClassLoader}
	 */
	public final ClassLoader getClassLoader() {
		return this.classLoader;
	}
	
	/**
	 * 根据给定资源初始化URL
	 */
	private void initUrl(){
		if(null != this.clazz){
			super.url = this.clazz.getResource(this.path);
		}else if(null != this.classLoader){
			super.url = this.classLoader.getResource(this.path);
		}else{
			super.url = ClassLoader.getSystemResource(this.path);
		}
		if(null == super.url){
			throw new IORuntimeException("Resource of path [{}] not exist!", this.path);
		}
	}
	
	@Override
	public String toString() {
		return (null == this.path) ? super.toString() : "classpath:" + this.path;
	}
}
