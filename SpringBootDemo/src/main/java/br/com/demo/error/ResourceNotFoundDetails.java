package br.com.demo.error;

import javax.annotation.Generated;

public class ResourceNotFoundDetails extends ErrorDetails{

	@Generated("SparkTools")
	private ResourceNotFoundDetails(Builder builder) {
		super(builder);
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
		
		private Builder() {
		}

		public ResourceNotFoundDetails build() {
			return new ResourceNotFoundDetails(this);
		}
	}

	
}
