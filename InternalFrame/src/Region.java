//package students.logic;

public class Region {

    private int regionId;
    private String nameRegion;
    private String aRegion_Description;
    // private String speciality;

    public String getRegion_Description() {
        return aRegion_Description;
    }

    public void setRegion_Description(String aRegion_Description) {
        this.aRegion_Description = aRegion_Description;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    // public String getSpeciality() {
        // return speciality;
    // }

    // public void setSpeciality(String speciality) {
        // this.speciality = speciality;
    // }

    public String toString() {
        return nameRegion;
    }
}