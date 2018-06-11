package br.com.demo.error;

import javax.annotation.Generated;

import br.com.demo.error.ResourceNotFoundDetails.Builder;

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
		setTitle(builder.title);
		setStatus(builder.status);
		setDetail(builder.detail);
		setTimestamp(builder.timestamp);
		setDeveloperMessage(builder.developerMessage);
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
	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;

		private Builder() {
		}

		public Builder withTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder withStatus(int status) {
			this.status = status;
			return this;
		}

		public Builder withDetail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder withTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder withDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}
		
		public Builder withField(String field) {
			this.field = field;
			return this;
		}
		
		public Builder withFieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}

		public ValidationErrorDetails build() {
			return new ValidationErrorDetails(this);
		}
	}	
}
