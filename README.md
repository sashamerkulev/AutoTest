# AutoTest


Простенькое тестовое приложеньице, читает из интернетов json (список) и показывает его, и, это только в одном активити.

Вторая активити viewpager для данных (список) полученных из первой активити, отличие только в способе отображения. Первая активити показывает данные в виде списка, то вторая показывает их в виде страницы, где на каждой странице есть информация из списка, с возможность листания списка.


Архитектурно, приложение содержит несколько package:
- presentation, содержит две активити одну для списка (ListView) и вторую для работы ViewPager (отображает картинку из интернета с помощью Picasso); также в пакете есть один презентер, который, дистрибьютит данные в обе активити, данные получает с http, презентер инжектится в активити с помощью Dagger2;
- data, содержит логику для работы с http, чтение json файла из интернета (используется Retrofit2);
- di, содержит модули и компопненты для работы Dagger2;
- model, содержит классы моделей данных.

