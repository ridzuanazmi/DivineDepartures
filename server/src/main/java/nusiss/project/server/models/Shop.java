package nusiss.project.server.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import nusiss.project.server.models.user.User;

@Entity
@Table(name = "shop")
public class Shop {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String deceasedName;
  private String block;
  private int plotNumber;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private Date dateOfDeath;
  private String tombstoneHeight;
  private String tombstoneMaterial;
  private String tiles;
  private String curvedMosaicTile;
  private String topCover;
  private String plant;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  
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
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  
  @Override
  public String toString() {
    return "Shop [id=" + id + ", deceasedName=" + deceasedName + ", block=" + block + ", plotNumber=" + plotNumber
        + ", dateOfDeath=" + dateOfDeath + ", tombstoneHeight=" + tombstoneHeight + ", tombstoneMaterial="
        + tombstoneMaterial + ", tiles=" + tiles + ", curvedMosaicTile=" + curvedMosaicTile + ", topCover=" + topCover
        + ", plant=" + plant + "]";
  }
  
}
