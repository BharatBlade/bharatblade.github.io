#include <IE.au3>
Local $oIE = _IECreate("http://139.127.40.17/reportconsole/ReportController")
WinSetState("Vocera Report Console Login","",@SW_MAXIMIZE)
Sleep(4000)
Local $oSubmit = _IEGetObjByName($oIE, "password")
_IEAction($oSubmit, "click")
Send("HRVOC03")
Sleep(1000)
Send("{ENTER}")
Sleep(4000)
Local $oSubmit1 = _IEGetObjByName($oIE, "device_management_link")
_IEAction($oSubmit1, "click")
Sleep(3000)
Local $i = 0
While $i < 26
   Send("{TAB}")
   $i = $i + 1
WEnd
$i = 0
;While $i < 1
;   Send("{DOWN}")
;   $i = $i + 1
;WEnd
Local $oSubmit2 = _IEGetObjByName($oIE, "save")
_IEAction($oSubmit2, "click")
Sleep(2000)
Send("{TAB}")
Send("{TAB}")
Send("7/1/2015")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("7/31/2015")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{DOWN}")
Send("{DOWN}")
Send("{TAB}")
Send("{TAB}")
Sleep(1000)
Send("{DOWN}")
Sleep(1000)
Send("{DOWN}")
Sleep(1000)
Send("{TAB}")
Sleep(1000)
Send("{TAB}")
Sleep(1000)
Send("{ENTER}")
Sleep(1000)
Send("{TAB}")
Sleep(1000)
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{DOWN}")
Send("{TAB}")
Send("{ENTER}")
Send("{DOWN}")
Send("{DOWN}")
Send("{DOWN}")
Send("{TAB}")
Send("{ENTER}")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")
Send("{TAB}")