package io.asfjava.ui.core.schema;

import java.lang.annotation.Annotation;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.SchemaDecoratorFactory;

class CustomStringSchema extends StringSchema {

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		Iterable<Annotation> it = beanProperty.getMember().annotations();
		it.forEach(
				annotation -> SchemaDecoratorFactory.getInstance().getDecorator(annotation.annotationType().getName())
						.ifPresent(decorator -> decorator.customizeSchema(beanProperty, this)));
	}

}
