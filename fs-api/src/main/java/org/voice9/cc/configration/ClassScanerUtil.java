package org.voice9.cc.configration;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Create by caoliang on 2020/8/23
 */
public class ClassScanerUtil implements ResourceLoaderAware {
    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);


    public static Set<Class<?>> scan(String[] basepackages, Class<? extends Annotation>... annoations) {
        ClassScanerUtil cs = new ClassScanerUtil();
        //需要扫描的注解
        if (ArrayUtils.isNotEmpty(annoations)) {
            for (Class anno : annoations) {
                cs.addIncludeFilter(new AnnotationTypeFilter(anno));
            }
        }
        Set<Class<?>> classes = new HashSet<>();
        for (String s : basepackages) {
            classes.addAll(cs.doScan(s));
        }
        return classes;
    }

    public static Set<Class<?>> scan(String basePackage, Class<? extends Annotation>... annoations) {
        return scan(new String[]{basePackage}, annoations);
    }

    private Set<Class<?>> doScan(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    if ((includeFilters.size() == 0 && excludeFilters.size() == 0) || matches(metadataReader)) {
                        try {
                            classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("IO failure during classpath scanning", e);
        }
        return classes;
    }

    protected boolean matches(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.excludeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return false;
            }
        }
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置解析器
     *
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }


    public void addIncludeFilter(TypeFilter typeFilter) {
        this.includeFilters.add(typeFilter);
    }

    public void addExcludeFilter(TypeFilter typeFilter) {
        this.excludeFilters.add(0, typeFilter);
    }

    public final ResourceLoader getResourceLoader() {
        return this.resourcePatternResolver;
    }

    public void resetResourceLoader() {
        this.includeFilters.clear();
        this.excludeFilters.clear();
    }

}