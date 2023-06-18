package nusiss.project.server.models;

public class MaintenanceCheckout {
  
  private String priceId;
  private String successUrl;
  private String cancelUrl;

  public String getPriceId() {
    return priceId;
  }
  public void setPriceId(String priceId) {
    this.priceId = priceId;
  }
  public String getSuccessUrl() {
    return successUrl;
  }
  public void setSuccessUrl(String successUrl) {
    this.successUrl = successUrl;
  }
  public String getCancelUrl() {
    return cancelUrl;
  }
  public void setCancelUrl(String cancelUrl) {
    this.cancelUrl = cancelUrl;
  }
  
  @Override
  public String toString() {
    return "MaintenanceCheckout [priceId=" + priceId + ", successUrl=" + successUrl + ", cancelUrl=" + cancelUrl + "]";
  }
  
}
