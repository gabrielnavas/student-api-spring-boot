package repository_spring.repository_spring.rest.dto;

public class UpdateStatusOrderDTO {
    private String newStatus;

    public UpdateStatusOrderDTO() {}

    public UpdateStatusOrderDTO( String newStatus) {
      this.newStatus = newStatus;
    }

    public String getNewStatus() {
      return newStatus;
    }

    public void setNewStatus(String newStatus) {
      this.newStatus = newStatus;
    }
}
