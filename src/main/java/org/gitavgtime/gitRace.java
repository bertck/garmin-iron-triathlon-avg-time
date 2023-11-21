package org.gitavgtime;

public class gitRace {

  private String name;
  private String id;
  private int avg10;
  private int avg20;
  private int avg50;
  private int avg100;
  private int avgN;

  public gitRace(String name, String id) {
    this.name = name;
    this.id = id;
    this.avg10 = 0;
    this.avg20 = 0;
    this.avg50 = 0;
    this.avg100 = 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAvg10() {
    return avg10;
  }

  public void setAvg10(int avg10) {
    this.avg10 = avg10;
  }

  public int getAvg20() {
    return avg20;
  }

  public void setAvg20(int avg20) {
    this.avg20 = avg20;
  }

  public int getAvg50() {
    return avg50;
  }

  public void setAvg50(int avg50) {
    this.avg50 = avg50;
  }

  public int getAvg100() {
    return avg100;
  }

  public void setAvg100(int avg100) {
    this.avg100 = avg100;
  }

  public int getAvgN() {
    return avgN;
  }

  public void setAvgN(int avgN) {
    this.avgN = avgN;
  }
}
