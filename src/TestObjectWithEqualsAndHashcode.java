import java.util.Objects;

public class TestObjectWithEqualsAndHashcode {
    private int anInt;
    private String string;

    public TestObjectWithEqualsAndHashcode(int anInt, String string) {
        this.anInt = anInt;
        this.string = string;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestObjectWithEqualsAndHashcode that = (TestObjectWithEqualsAndHashcode) o;
        return anInt == that.anInt &&
                Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {

        return Objects.hash(anInt, string);
    }
}
