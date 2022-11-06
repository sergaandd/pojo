package shpp.mentor;

import jakarta.validation.constraints.*;
import java.util.Objects;

public class Pojo {
    @NotNull(message = "Count cannot be null")
    @Min(value=10, message = "Count should be more than 10")
    private int count;

    @Size(min=7, message = "Name should be more 7 symbols")
    @Pattern(regexp ="[\\w]*[Aa]+[\\w]*",message = "No a in name")
    private String name;

    private String created_at;

    Pojo(){
    }

    public int getCount() {
        return count;
    }

    public Pojo setCount(int count) {
        this.count = count;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pojo setName(String name) {
        this.name = name;
        return this;
    }

    public String getCreated_at() {
        return created_at;
    }

    public Pojo setCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pojo pojo = (Pojo) o;
        return count == pojo.count && Objects.equals(name, pojo.name) && Objects.equals(created_at, pojo.created_at) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, name, created_at);
    }
}