package br.com.demo.error;

import javax.annotation.Generated;

public class ValidationErrorDetails extends ErrorDetails{

	private String field;
	private String fieldMessage;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}

	public void setFieldMessage(String fieldMessage) {
		this.fieldMessage = fieldMessage;
	}

	@Generated("SparkTools")
	private ValidationErrorDetails(Builder builder) {
		super(builder);
		setField(builder.field);
		setFieldMessage(builder.fieldMessage);
		
	}

	/**
	 * Creates builder to build {@link ResourceNotFoundDetails}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ResourceNotFoundDetails}.
	 */
	@Generated("SparkTools")
	public static final class Builder extends ErrorDetails.Builder{
		private String field;
		private String fieldMessage;

		private Builder() {
			super();
		}
		
		public Builder field(String field) {
			this.field = field;
			return this;
		}
		
		public Builder fieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}

		public ValidationErrorDetails build() {
			return new ValidationErrorDetails(this);
		}
	}	
}
