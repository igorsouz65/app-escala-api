package igor.escalaspring.error;

import jakarta.annotation.Generated;

public class ResourceNotFoundDetails extends ErrorDetails{

	@Generated("SparkTools")
	private ResourceNotFoundDetails(Builder builder) {
		super();
	}
	
	private ResourceNotFoundDetails() {
		super();
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
		private Long timestamp;
		private String developerMessage;

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

		public Builder withTimestamp(Long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder withDeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public ResourceNotFoundDetails build() {
			ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
			resourceNotFoundDetails.setDeveloperMessage(this.developerMessage);
			resourceNotFoundDetails.setTitle(this.title);
			resourceNotFoundDetails.setDetail(this.detail);
			resourceNotFoundDetails.setTimestamp(this.timestamp);
			resourceNotFoundDetails.setStatus(this.status);
			return resourceNotFoundDetails;
		}
	}
	
	
	
	
}
