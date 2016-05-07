/*
*######################################
* eWebEditor V9.0 - Advanced online web based WYSIWYG HTML editor.
* Copyright (c) 2003-2013 eWebSoft.com
*
* For further information go to http://www.ewebeditor.net/
* This copyright notice MUST stay intact for use.
*######################################
*/

(function(){var AX=window.jX=function(){var eC=window.document.body;if(eC.clientWidth==0||eC.clientHeight==0){return false;}for(var i=0;i<eC.childNodes.length;i++){var child=eC.childNodes[i];switch(child.className){case "tr":child.style.left=Math.max(0,eC.clientWidth-tr.clientWidth);break;case "tc":child.style.width=Math.max(0,eC.clientWidth-tl.clientWidth-tr.clientWidth);break;case "ml":child.style.height=Math.max(0,eC.clientHeight-tl.clientHeight-bl.clientHeight);break;case "mr":child.style.left=Math.max(0,eC.clientWidth-mr.clientWidth);child.style.height=Math.max(0,eC.clientHeight-tr.clientHeight-br.clientHeight);break;case "mc":child.style.width=Math.max(0,eC.clientWidth-ml.clientWidth-mr.clientWidth);child.style.height=Math.max(0,eC.clientHeight-TitleArea.clientHeight-bc.clientHeight);break;case "bl":child.style.top=Math.max(0,eC.clientHeight-bl.clientHeight);break;case "br":child.style.left=Math.max(0,eC.clientWidth-br.clientWidth);child.style.top=Math.max(0,eC.clientHeight-br.clientHeight);break;case "bc":child.style.width=Math.max(0,eC.clientWidth-bl.clientWidth-br.clientWidth);child.style.top=Math.max(0,eC.clientHeight-bc.clientHeight);break;}}return true;};window.xy=function(){if(!window.jX()){window.setTimeout(window.xy,1);return;}ec.oP(window.frameElement);};var yZ=function(){this.className="TitleCloseButtonHover";};var xs=function(){this.className="TitleCloseButton";};var zd=function(){var yt=document.getElementById("TitleCloseButton");yt.onmouseover=yZ;yt.onmouseout=xs;};var onLoad=function(){zd();window.detachEvent("onload",onLoad);};window.attachEvent("onload",onLoad);})(); 