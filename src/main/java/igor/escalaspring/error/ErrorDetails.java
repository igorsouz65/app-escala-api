package igor.escalaspring.error;

import javax.annotation.Generated;

public class ErrorDetails {
	private String title;
	private int status;
	private String  detail;
	private Long timestamp;
	private String developerMessage;


	@Generated("SparkTools")
	private ErrorDetails(Builder builder) {
		this.title = builder.title;
		this.status = builder.status;
		this.detail = builder.detail;
		this.timestamp = builder.timestamp;
		this.developerMessage = builder.developerMessage;
	}
	
	public ErrorDetails() {
		super();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	/**
	 * Creates builder to build {@link ErrorDetails}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder newBuilder() {
		return new Builder();
	}
	/**
	 * Builder to build {@link ErrorDetails}.
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

		public ErrorDetails build() {
			return new ErrorDetails(this);
		}
	}
	
	
}
