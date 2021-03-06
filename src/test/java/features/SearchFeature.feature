# language: ru
@withdrawal
Функция: Сумма НЦ всех закупок
    @success
    Сценарий: Просчёт суммы по всем закупкам за данное число
        Дано пользователь переходит на форму поиска
        Когда пользователь выбирает дату начала публикации "26.04.2019"
        И выбирает дату окончания публикации "26.04.2019"
        И выбирает начальную стоимость закупки "0"
        И отмечает \"Закупка в соответсвии с нормами два-два-три-ФЗ\"
        И отмечает \"Коммерческая закупка\"
        И нажимает кнопку поиска
        Тогда сумма всех закупок равна