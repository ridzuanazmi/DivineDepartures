package nusiss.project.server.models;

import java.sql.Date;

// To match POST /email/shop request body
public class ShopDto {

  private Integer id;
  private String deceasedName;
  private String block;
  private int plotNumber;
  private Date dateOfDeath;
  private String tombstoneHeight;
  private String tombstoneMaterial;
  private String tiles;
  private String curvedMosaicTile;
  private String topCover;
  private String plant;
  private String email;
  private String fullName;
  private String phoneNumber;
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeceasedName() {
    return deceasedName;
  }

  public void setDeceasedName(String deceasedName) {
    this.deceasedName = deceasedName;
  }

  public String getBlock() {
    return block;
  }

  public void setBlock(String block) {
    this.block = block;
  }

  public int getPlotNumber() {
    return plotNumber;
  }

  public void setPlotNumber(int plotNumber) {
    this.plotNumber = plotNumber;
  }

  public Date getDateOfDeath() {
    return dateOfDeath;
  }

  public void setDateOfDeath(Date dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }

  public String getTombstoneHeight() {
    return tombstoneHeight;
  }

  public void setTombstoneHeight(String tombstoneHeight) {
    this.tombstoneHeight = tombstoneHeight;
  }

  public String getTombstoneMaterial() {
    return tombstoneMaterial;
  }

  public void setTombstoneMaterial(String tombstoneMaterial) {
    this.tombstoneMaterial = tombstoneMaterial;
  }

  public String getTiles() {
    return tiles;
  }

  public void setTiles(String tiles) {
    this.tiles = tiles;
  }

  public String getCurvedMosaicTile() {
    return curvedMosaicTile;
  }

  public void setCurvedMosaicTile(String curvedMosaicTile) {
    this.curvedMosaicTile = curvedMosaicTile;
  }

  public String getTopCover() {
    return topCover;
  }

  public void setTopCover(String topCover) {
    this.topCover = topCover;
  }

  public String getPlant() {
    return plant;
  }

  public void setPlant(String plant) {
    this.plant = plant;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String toString() {
    return "ShopDto [id=" + id + ", deceasedName=" + deceasedName + ", block=" + block + ", plotNumber=" + plotNumber
        + ", dateOfDeath=" + dateOfDeath + ", tombstoneHeight=" + tombstoneHeight + ", tombstoneMaterial="
        + tombstoneMaterial + ", tiles=" + tiles + ", curvedMosaicTile=" + curvedMosaicTile + ", topCover=" + topCover
        + ", plant=" + plant + ", email=" + email + "]";
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

}
