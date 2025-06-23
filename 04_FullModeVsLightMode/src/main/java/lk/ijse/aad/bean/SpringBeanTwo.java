package lk.ijse.aad.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class SpringBeanTwo implements DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean  {
    public SpringBeanTwo() {
        System.out.println("SpringBeanTwo constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpringBeanTwo setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("SpringBeanTwo setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("SpringBeanTwo setApplicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SpringBeanTwo afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("SpringBeanTwo destroy");
    }
}
