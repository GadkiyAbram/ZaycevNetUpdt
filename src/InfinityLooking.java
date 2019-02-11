import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class InfinityLooking {
    private List<Integer> initData;         //Данные, вводимые пользователем, исходный произвольный массив данных
    private Integer reMade;                 //Переменная, хранящая хэш искомого цикла (задание 2)
    private Integer count = 1;              //Задание 1. Искомое количество шагов до получения бесконечности
    private Integer loopLength = 0;         //Задание 2. Искомая длина получившегося цикла
    private Set<Integer> finalSet;          //Финальный набор для поиска заданий задачи

    public InfinityLooking(List<Integer> data){        //Конструктор класса,
        this.initData = data;                           //принимает массив, введенный пользователем
        this.finalSet = new LinkedHashSet<>();          //Инициализируем финальный набор будущих хэшей
    }

    //Метод, обрабатывающий входной массив пользователя, возвращающий массив,
    //максимальный элемент которого распределен между остальными членами массива, начиная со следующего

    private List<Integer> transforEnteredList(){

        // 1. Поиск максимального по значению элемента входного массива (data)
        int initPos;                                    //Начальное положение счетчика для начала распределения максимального значения
        int index = 0;                                  //Инициализация позиции максимального по значению элемента
        int max = 0;                                    //Инициализация максимального значения массива (data)
        for (int i = 0; i < initData.size(); i++){          //Цикл поиска максимального значения во входном массиве
            if (initData.get(i) > max){
                max = initData.get(i);                      //Запоминаем макс значение
                index = i;                              //Запоминаем индекс элемента с макс значением
            }
        }

        initData.set(index, 0);                                 //Обнуление максимального значения по индексу

        // 2. Перераспределение максимального значения между элементами массива
        int amountToDistribute = max / (initData.size());       //Рассчитываем число, необходимое распределить
                                                                // между всеми членами массива (Integer округлит
                                                                //в меньшую сторону до целого)
        int remaining = max % (initData.size());                //Расчет остатка распределения

        for (int i = 0; i < initData.size(); i++){              //Распределяем с 0-го элемента массива
            int newVal = initData.get(i) + amountToDistribute;
            initData.set(i, newVal);
        }

        if (index >= initData.size() - 1){                      //Определяем позицию элемента,
            initPos = 0;                                        //с какого распределять остаток
        }else {
            initPos = index + 1;
        }

        while (remaining > 0){                                  //Распределение остатка
            int newVal = initData.get(initPos) + 1;             //начиная со следующего элемента
            initData.set(initPos, newVal);                      //после максимального
            remaining--;
            initPos++;

            if (initPos >= initData.size()){
                initPos = 0;
            }
        }

        return initData;                            //Возвращаем переработанный массив
    }

    public void countUntilMeet(List<Integer> data){   //Функция, возвращающая пару "шаги до бесконечного цикла + длину цикла"
        Integer arrayHash;                            //Переменная, хранящая в себе хэш перераспределенного массива
        boolean contain = false;                      //Переменная, содержащая булево значение содержит/не содержит массив хэш перераспределенного массива
        count = 1;                                    //Переменная, считающая количество шагов = 1, т.к. первое вхождение хэша уже имеем
        while (contain == false){
            data = transforEnteredList();             //На вход подается перераспределяемый массив (функция описана выше)
            arrayHash = data.hashCode();              //Считается хэш
            if (finalSet.contains(arrayHash)){        //Проверка содержания хэша в наборе хжшей
                contain = true;                       //Если содержит - цикл завершен
                reMade = arrayHash;                   //Запоминаем его хэш
            }else {                                   //Если не содержит,
                finalSet.add(data.hashCode());        // то добавляем этот хэш в набор хэшей
                count++;                              //Увеличиваем счетчик
            }
        }

        Boolean flag = false;                         //Флаг, устанавливающийся в true если хэш найден

        for (Integer hashItems : finalSet){           //Среди всех хэшей ищем первое совпадение
            if (hashItems.equals(reMade)){
                flag = true;                          //Устанавливаем флаг = true
            }
            if (flag == true){
                loopLength++;                         //Считаем длину цикла
            }
        }
    }

    public void showResult(){                       //Функция печатает результат
        System.out.println("Бесконечный цикл найден за (шагов): " + count + "\n"
                + "и его длина: " + loopLength);
    }
}
