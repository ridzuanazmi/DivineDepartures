package nusiss.project.server.models;

public class MaintenanceCheckout {

  private String priceId;
  private String successUrl;
  private String cancelUrl;
  private String blockNumber;
  private String plotNumber;
  private String fullName;
  private String email;

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
  public String getPlotNumber() {
    return plotNumber;
  }
  public void setPlotNumber(String plotNumber) {
    this.plotNumber = plotNumber;
  }
  public String getBlockNumber() {
    return blockNumber;
  }
  public void setBlockNumber(String blockNumber) {
    this.blockNumber = blockNumber;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  @Override
  public String toString() {
    return "MaintenanceCheckout [priceId=" + priceId + ", successUrl=" + successUrl + ", cancelUrl=" + cancelUrl
        + ", blockNumber=" + blockNumber + ", plotNumber=" + plotNumber + ", fullName=" + fullName + ", email=" + email
        + "]";
  }
}
