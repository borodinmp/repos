<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Message Editor
</a>
<div class="collapse <#if message??>show</#if> mt-3" id="collapseExample"
     <div class="form-group">
         <form method="post" action="text" enctype="multipart/form-data">
             <input type="hidden" name="_csrf" value="${_csrf.token}"/>
             <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
             <div class="form-group">
                 Наименование организации<input type="text" class="form-control ${(nameOrgError??)?string('is-invalid', '')}"
                                                value = "<#if message ??>${message.nameOrg}</#if>"name="nameOrg" placeholder="Введите наименование организации">
                 <#if nameOrgError??>
                      <div class="invalid-feedback">
                          ${nameOrgError}
                      </div>
                 </#if>
             </div>
             <div class="form-group">
                 ИНН <input class="form-control ${(innError??)?string('is-invalid', '')}"
                            value = "<#if message ??>${message.inn}</#if>" type="text" name="inn" placeholder="Введите ИНН организации">
                 <#if innError??>
                      <div class="invalid-feedback">
                          ${innError}
                      </div>
                 </#if>
             </div>
             <div class="form-group">
                 ОГРН <input class="form-control ${(ogrnnError??)?string('is-invalid', '')}"
                             value = "<#if message ??>${message.ogrnn}</#if>" type="text" name="ogrnn" placeholder="Введите ОГРН организации">
                 <#if ogrnnError??>
                      <div class="invalid-feedback">
                          ${ogrnnError}
                      </div>
                 </#if>
             </div>
             <div class="form-group">
                 Адрес<input class="form-control ${(addressOrgError??)?string('is-invalid', '')}"
                             value = "<#if message ??>${message.addressOrg}</#if>" type="text" name="addressOrg" placeholder="Введите адрес организации">
                 <#if addressOrgError??>
                      <div class="invalid-feedback">
                          ${addressOrgError}
                      </div>
                 </#if>
             </div>
             <div class="custom-file">
                 <input  type="file" name="file" id = customFile>
                 <label class="custom-file-label" for="customFile">Choose file</label>
             </div>
             <button class="btn btn-primary mt-3" type="submit">Save info</button>
         </form>
     </div>
</div>