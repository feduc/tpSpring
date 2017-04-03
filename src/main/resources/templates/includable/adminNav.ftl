<div class="col-xs-12 col-sm-4 col-md-4">
         <div class="changeavis">
            <p style="align:center" ><font color="white"><b>Creation/Modification</b></font></p>
            <#if admin == true>
                <form id= "formid" action="/user/create/" method="get">
                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                    <input style="width:75%" type="submit" value="Utilisateur"/>
                </form>
            <#else>
             </#if>

            <#if admin == true>
                <form id= "formid" action="/admin/chooseUser" method="get">
                    <div>
                        <#include "../includable/security/securityToken.ftl">
                    </div>
                    <input style="width:75%" type="submit" value="Roles d'un utilisateur"/>
                </form>
            <#else>
             </#if>

            <form id= "formid" action="/project/create/" method="get">
                <div>
                    <#include "../includable/security/securityToken.ftl">
                </div>
                <input style="width:75%" type="submit" value="Projet"/>
            </form>


            <form id= "formid" action="/admin/choose" method="get">
                <div>
                    <#include "../includable/security/securityToken.ftl">
                </div>
                <input style="width:75%" type="submit" value="Membres d'un projet"/>
            </form>

            <hr style= "width:75%"/>

            <p style="align:center" ><font color="white"><b>Perso</b></font></p>

            <form id= "formid" action="/user/resume/" method="get">
                <div>
                    <#include "../includable/security/securityToken.ftl">
                </div>
            <input style="width:75%" type="submit" value="Ma page perso"/>
            </form>

            <form id= "formid" action="/mood/vote/" method="get">
                <div>
                    <#include "../includable/security/securityToken.ftl">
                </div>
            <input style="width:75%" type="submit" value="Voter"/>
            </form>
            <br/>

          </div>
      </div>