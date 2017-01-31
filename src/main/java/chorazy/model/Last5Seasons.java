package chorazy.model;

import com.google.common.base.Objects;

import java.util.LinkedList;
import java.util.stream.DoubleStream;

class Last5Seasons {

    private static final int LIMIT = 5;
    private LinkedList<Season> list = new LinkedList<>();

    void add(Season season) {
        season.roundScore();
        list.add(season);
        while (list.size() > LIMIT) {
            list.remove();
        }
    }

    int avg() {
        return (int) Math.round(list
                .stream()
                .flatMapToDouble(season -> DoubleStream.of(season.getScore()))
                .average()
                .getAsDouble()
        );
    }

    public LinkedList<Season> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Last5Seasons" +
                list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Last5Seasons that = (Last5Seasons) o;
        return Objects.equal(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(list);
    }
}
