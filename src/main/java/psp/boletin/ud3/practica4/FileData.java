package psp.boletin.ud3.practica4;

import java.time.LocalDate;

public class FileData {

    private String name;
    private long size;
    LocalDate localDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", localDate=" + localDate +
                '}';
    }
}
