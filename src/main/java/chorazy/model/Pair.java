package chorazy.model;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

public class Pair {

    private final Country element1;
    private final Country element2;

    private Pair(Country element1, Country element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public Country element1() {
        return element1;
    }

    public Country element2() {
        return element2;
    }

    public static List<Pair> createPairs(List<Country> countries) {
        int size = countries.size();
        Element[] elements = Element.createArray(size);
        List<Pair> pairs = new ArrayList<>();
        int sizeBefore;
        for (int i = 0; i < size * 2; i++) {
            sizeBefore = pairs.size();
            stream(elements)
                    .forEach(e1 -> {
                        if (!e1.taken) {
                            e1.setTaken();
                            stream(elements)
                                    .filter(Element::notTaken)
                                    .filter(e2 -> !pairs.contains(new Pair(countries.get(e1.value), countries.get(e2.value))))
                                    .findFirst()
                                    .ifPresent(e2 -> {
                                        e2.setTaken();
                                        pairs.add(new Pair(countries.get(e1.value), countries.get(e2.value)));
                                    });
                        }
                    });
            if (sizeBefore == pairs.size()) {
                break;
            }
            stream(elements).forEach(Element::setNotTaken);
        }
        return pairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return element1 == pair.element1 &&
                element2 == pair.element2;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(element1, element2);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "element1=" + element1 +
                ", element2=" + element2 +
                '}';
    }

    static class Element {
        int value;
        boolean taken;

        Element(int value) {
            this.value = value;
            this.taken = false;
        }

        boolean notTaken() {
            return !this.taken;
        }

        void setTaken() {
            this.taken = true;
        }

        void setNotTaken() {
            this.taken = false;
        }

        static Element[] createArray(int size) {
            Element[] elements = new Element[size];
            for (int i = 0; i < size; i++) {
                elements[i] = new Element(i);
            }
            return elements;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "value=" + value +
                    ", taken=" + taken +
                    '}';
        }
    }
}
