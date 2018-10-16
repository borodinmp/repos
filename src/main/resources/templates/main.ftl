<#import "parts/common.ftl" as c>
<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <select name="orgFilter">
                <option name="orgAll" value="1">Все поля</option>
                <option name="orgId">ID</option>
                <option name="orgName">Наименование организации</option>
                <option name="orgInn">ИНН</option>
                <option name="orgOgrn">ОГРН</option>
                <option name="orgAddress">Адрес</option>
            </select>
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search" class="form-control ml-2">
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse mt-3" id="collapseExample"
     <div class="form-group">
         <form method="post" action="text" enctype="multipart/form-data">
             <input type="hidden" name="_csrf" value="${_csrf.token}"/>
             <div class="form-group">
                 Наименование организации<input class="form-control" type="text" name="nameOrg" placeholder="Введите наименование организации">
             </div>
             <div class="form-group">
                 ИНН <input class="form-control" type="text" name="inn" placeholder="Введите ИНН организации">
             </div>
             <div class="form-group">
                 ОГРН <input class="form-control" type="text" name="ogrnn" placeholder="Введите ОГРН организации">
             </div>
             <div class="form-group">
                 Адрес<input class="form-control" type="text" name="addressOrg" placeholder="Введите адрес организации">
             </div>
             <div class="custom-file">
                 <input  type="file" name="file" id = customFile>
                 <label class="custom-file-label" for="customFile">Choose file</label>
             </div>
             <button class="btn btn-primary mt-3" type="submit">Отправить</button>
         </form>
     </div>
</div>

<br>

<div>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Наименование организации</th>
            <th>Инн</th>
            <th>ОГРН</th>
            <th>Адрес</th>
            <th>Автор</th>
            <th>Картинка</th>
        </thead>
        <tbody>
        </tr>
        <#list messages as message>
        <tr>
            <td>${message.id}</td>
            <td>${message.nameOrg}</td>
            <td>${message.inn}</td>
            <td>${message.ogrnn}</td>
            <td>${message.addressOrg}</td>
            <td>${message.authorName}</td>
            <td>
                <#if message.filename??>
                <img class="img-thumbnail" width="100" height="100" src="/img/${message.filename}"
            </#if></td>
        </tr>
        </tbody>
        <#else>
        No message
        </#list>
    </table>
</div>

<div>Удаление элементов</div>
<div class="form-inline">
<form method="post" action="delete">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="text" name="id">
    <button type="submit">Удалить</button>
</form>
</div>

</@c.page>