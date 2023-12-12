package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {
    //вынесем индекс в константу
    public static final int INDEX = 0;
    private long count;

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    //Лучше не менять исходный лист, а сделать его кописю и работать с ней
    public List<String> getNames(List<Person> persons) {
        if (persons.isEmpty()) {
            return Collections.emptyList();
        }

        // Создаем копию списка, чтобы избежать изменения оригинального списка
        List<Person> personsCopy = new ArrayList<>(persons);

        // Удаляем первого человека из копии списка
        personsCopy.remove(INDEX);

        // Возвращаем имена оставшихся людей
        return personsCopy.stream()
                .map(Person::getFirstName)
                .toList();
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getFirstName) // Используем map для извлечения имен
                .collect(Collectors.toSet()); // Создаем Set, чтобы получить уникальные имена
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    //Так короче
    public String convertPersonToString(Person person) {
        return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }

    // словарь id персоны -> ее имя
    // стрим тут выглядит приятнее для чтения
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        return persons.stream()
                .collect(Collectors.toMap(Person::getId,
                        this::convertPersonToString,
                        (existing, replacement) -> existing));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    //можно написать более лакончное сравнение
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        return persons1.stream()
                .anyMatch(persons2::contains);
    }

    //...
    //лучше использовать каунт
    public long countEven(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0)
                .count();
    }
}
