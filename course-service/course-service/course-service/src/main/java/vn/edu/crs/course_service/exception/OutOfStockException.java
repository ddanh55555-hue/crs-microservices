package vn.edu.crs.course_service.exception;

public class OutOfStockException extends RuntimeException {
    private final Integer soChoConLai;

    public OutOfStockException(String message, Integer soChoConLai) {
        super(message);
        this.soChoConLai = soChoConLai;
    }

    public Integer getSoChoConLai() {
        return soChoConLai;
    }
}