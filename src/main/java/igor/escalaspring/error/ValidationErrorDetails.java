package igor.escalaspring.error;

import jakarta.annotation.Generated;

public class ValidationErrorDetails extends ErrorDetails{
	private String field;
	private String fieldMessage;
	
	@Generated("SparkTools")
	private ValidationErrorDetails(Builder builder) {
		super();
	}
	
	
	public ValidationErrorDetails() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Creates builder to build {@link ValidationErrorDetails}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ValidationErrorDetails}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private Long timestamp;
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

		public Builder withTimestamp(Long timestamp) {
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
			ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
			validationErrorDetails.setDeveloperMessage(this.developerMessage);
			validationErrorDetails.setTitle(this.title);
			validationErrorDetails.setDetail(this.detail);
			validationErrorDetails.setTimestamp(this.timestamp);
			validationErrorDetails.setStatus(this.status);
			validationErrorDetails.field = field;
			validationErrorDetails.fieldMessage = fieldMessage;
			return validationErrorDetails;
		}
	}

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
	
}
