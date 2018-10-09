<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<div>
<@l.logout />
</div>

<div>
    <form method="post" action="text">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div>Наименование организации  <input type="text" name="nameOrg" placeholder="Введите наименование организации"></div>
        <div>ИНН  <input type="text" name="inn" placeholder="Введите ИНН организации"></div>
        <div>ОГРН  <input type="text" name="ogrnn" placeholder="Введите ОГРН организации"></div>
        <div>Адрес  <input type="text" name="addressOrg" placeholder="Введите адрес организации"></div>
        <button type="submit">Отправить</button>
    </form>
</div>

<br>
<div>Поиск по таблице</div>
<form method="get" action="/main">
    <select name="orgFilter" >
        <option value="orgAll">Все поля</option>
        <option value="orgId">ID</option>
        <option value="orgName">Наименование организации</option>
        <option value="orgInn">ИНН</option>
        <option value="orgOgrn">ОГРН</option>
        <option value="orgAddress">Адрес</option>
    </select>
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>
<br>

<div>
    <table border="1">
        <tr>
        <th>ID</th>
        <th>Наименование организации</th>
        <th>Инн</th>
        <th>ОГРН</th>
        <th>Адрес</th>
        <th>Автор</th>
        </tr>
        <#list messages as message>
        <tr>
            <td>${message.id}</td>
            <td>${message.nameOrg}</td>
            <td>${message.inn}</td>
            <td>${message.ogrnn}</td>
            <td>${message.addressOrg}</td>
            <td>${message.authorName}</td>
        </tr>
        <#else>
        No message
        </#list>
    </table>
</div>


<div>Удаление элементов</div>
<form method="post" action="delete">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <input type="text" name="id">
    <button type="submit">Удалить</button>
</form>

</@c.page>