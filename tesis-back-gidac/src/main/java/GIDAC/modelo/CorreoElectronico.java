/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GIDAC.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author My Notebook
 */
public class CorreoElectronico {
    private String destinatario;
    private String asunto;
    private String contenido;
    
    //registro de usuario
    public String reseteoContraseniaUsuario(Usuario usuario){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); " +
                        "   </style>" +
                
                        "     <title>Reseteo contraseña</title>" +
                        " </head> " +
                        " <body > " +
                       
                        "     <div align='center'> " +

                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:\"Rubik Microbe\",sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(usuario.getFechaActualizacion())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong>RESETEO DE CONTRASEÑA</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+usuario.getNombreUsuario()+" "+ usuario.getApellidoUsuario()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Se ha reseteado su contraseña exitosamente:</td> " +
                        "                 </tr> " +

                        "                 <tr> "+
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:left;line-height:22px'> " +
                        "                         <table align='center' border='0' cellpadding='0' cellspacing='0' " +
                        "                             style='border-collapse:collapse;border:1px solid #f6f4f5' width='440'> " +
                        "                             <tbody> " +

                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Correo:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getEmail()+"</td> " +
                        "                                 </tr> " +
                                
                        
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Contraseña:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getContrasenia()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                             </tbody> " +
                        "                         </table> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> " +
                        "                         Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                               
                                
                                
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                        "     </div> " +
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    //registro de usuario
    public String registrarUsuario(Usuario usuario){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); +" +
                        "   </style>" +
                
                        "     <title>Registrar datos usuario</title>" +
                        " </head> " +
                        " <body> " +
                        "     <div align='center'> " +
                
                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:Rubik Microbe,sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(usuario.getFechaCreacion())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong>REGISTRO DE "+usuario.getRol().getNombreRol()+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+usuario.getNombreUsuario()+" "+ usuario.getApellidoUsuario()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Se ha registrado exitosamente los siguientes datos:</td> " +
                        "                 </tr> " +

                        "                 <tr> "+
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:left;line-height:22px'> " +
                        "                         <table align='center' border='0' cellpadding='0' cellspacing='0' " +
                        "                             style='border-collapse:collapse;border:1px solid #f6f4f5' width='440'> " +
                        "                             <tbody> " +

                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Nombre:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getNombreUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Apellido:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getApellidoUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Cédula:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getCedula()+"</td> " +
                        "                                 </tr> " +
                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Correo:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getEmail()+"</td> " +
                        "                                 </tr> " +
                                
                        
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Contraseña:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getContrasenia()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                             </tbody> " +
                        "                         </table> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> " +
                        "                         Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                                
                                
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                                
                        "     </div> " +
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    //registro de usuario
    public String actualizacionPerfilUsuario(Usuario usuario){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        "    <link rel='preconnect' href='https://fonts.googleapis.com'> " +
                        "    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin> " +
                        "    <link href='https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap' rel='stylesheet'> " +
                
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); +" +
                        "   </style>" +
                
                        "     <title>Registrar datos usuario</title>" +
                        " </head> " +
                        " <body> " +
                        "     <div align='center'> " +
               
                        
                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:Rubik Microbe,sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(usuario.getFechaCreacion())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong>ACTUALIZACIÓN DE PERFIL DEL "+usuario.getRol().getNombreRol()+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+usuario.getNombreUsuario()+" "+ usuario.getApellidoUsuario()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Se ha actualizado exitosamente los siguientes datos:</td> " +
                        "                 </tr> " +

                        "                 <tr> "+
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:left;line-height:22px'> " +
                        "                         <table align='center' border='0' cellpadding='0' cellspacing='0' " +
                        "                             style='border-collapse:collapse;border:1px solid #f6f4f5' width='440'> " +
                        "                             <tbody> " +

                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Nombre:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getNombreUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Apellido:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getApellidoUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Cédula:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getCedula()+"</td> " +
                        "                                 </tr> " +
                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Correo:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getEmail()+"</td> " +
                        "                                 </tr> " +
                                
                        
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Contraseña:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getContrasenia()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                             </tbody> " +
                        "                         </table> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> " +
                        "                         Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                                
                                
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                        "     </div> " +        
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    
    //actualizacion de usuario
    public String actualizarUsuario(Usuario usuario){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        "    <link rel='preconnect' href='https://fonts.googleapis.com'> " +
                        "    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin> " +
                        "    <link href='https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap' rel='stylesheet'> " +
                
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); +" +
                        "   </style>" +
                
                        "     <title>Actualizar datos usuario</title>" +
                        " </head> " +
                        " <body> " +
                        "     <div align='center'> " +
                
                
                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                        
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:Rubik Microbe,sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(usuario.getFechaActualizacion())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong>ACTUALIZACIÓN DE "+usuario.getRol().getNombreRol()+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+usuario.getNombreUsuario()+" "+ usuario.getApellidoUsuario()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Se ha actualizado exitosamente los siguientes datos:</td> " +
                        "                 </tr> " +

                        "                 <tr> "+
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:left;line-height:22px'> " +
                        "                         <table align='center' border='0' cellpadding='0' cellspacing='0' " +
                        "                             style='border-collapse:collapse;border:1px solid #f6f4f5' width='440'> " +
                        "                             <tbody> " +

                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Nombre:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getNombreUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Apellido:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getApellidoUsuario()+"</td> " +
                        "                                 </tr> " +
                                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Cédula:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getCedula()+"</td> " +
                        "                                 </tr> " +
                
                                
                        "                                 <tr> " +
                        "                                     <td width='0%' align='right' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:20px;padding-bottom:5px;text-align:right;line-height:16px'> " +
                        "                                         <strong>Correo:</strong> " +
                        "                                     </td> " +
                        "                                     <td width='60%' align='left' bgcolor='#ffffff' " +
                        "                                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:5px;padding-right:3px;padding-left:3px;padding-bottom:5px;text-align:left;line-height:16px'>"+ usuario.getEmail()+"</td> " +
                        "                                 </tr> " +
                                
                        "                             </tbody> " +
                        "                         </table> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> " +
                        "                         Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                        
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                                
                        "     </div> " +        
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    //aprobacion de solicitud
    public String aprobacionSolicitud(RespuestaSolicitudDescarga solicitudDescarga){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        "    <link rel='preconnect' href='https://fonts.googleapis.com'> " +
                        "    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin> " +
                        "    <link href='https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap' rel='stylesheet'> " +
                
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); +" +
                        "   </style>" +
                
                        "     <title>Respuesta de solicitud</title>" +
                        " </head> " +
                        " <body> " +
                
                        "     <div align='center'> " +
                
                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:Rubik Microbe,sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(solicitudDescarga.getFechaRespuesta())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong> RESPUESTA DE SOLICITUD PARA DESCARGA DE DATOS </strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+solicitudDescarga.getSolicitudDescarga().getNombre()+" "+ solicitudDescarga.getSolicitudDescarga().getApellido()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Su solicitud para descargar datos del proyeco: <strong>"+solicitudDescarga.getSolicitudDescarga().getProyectoInvestigacion().getNombreProyecto()+"</strong>, a sido: </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong> APROBADA </strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                                
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> Se adjunta el archivo con los datos del proyecto. Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                                
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                        "     </div> " +       
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    //rechazo de solicitud
    public String rechazoSolicitud(RespuestaSolicitudDescarga solicitudDescarga){
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<!DOCTYPE html> "+
                        " <html xmlns:th=\"http://www.thymeleaf.org\">"+
                        "  <head>" +
                        "    <meta charset='UTF-8'> " +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
                        "    <link rel='preconnect' href='https://fonts.googleapis.com'> " +
                        "    <link rel='preconnect' href='https://fonts.gstatic.com' crossorigin> " +
                        "    <link href='https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap' rel='stylesheet'> " +
                
                        "   <style>" +
                        "   @import url('https://fonts.googleapis.com/css2?family=Rubik+Microbe&display=swap'); +" +
                        "   </style>" +
                
                
                        "     <title>Respuesta de solicitud</title>" +
                        " </head> " +
                        " <body> " +
                
                        "     <div align='center'> " +
                
                        "     <tbody> " +
                        "     <tr> " +
                        "     <td> " +
                        "     <table width='550' cellpadding='0' cellspacing='0' border='0' align='center'> " +
                        "     <tbody> " +
                        "     <tr> " +
                
                
                
                        "     <td width='100%' bgcolor='#f6f4f5' style='padding:20px'> " +
                        "         <table width='510' align='center' cellspacing='0' cellpadding='0' border='0'> " +
                        "             <tbody> " +
                        "                 <tr> " +
                        "                     <td align='center' bgcolor='#ffffff' " +
                        "                         style='color:white;background-color:rgb(107, 114, 95);font-size:40px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:center;'> " +
                        "                         <div style='font-family:Rubik Microbe,sans-serif; '> " +
                        "                             <div style='font-size: 40px;'>EcoAndes</div> " +
                        "                         </div> " +
                        "                     </td> " +
                        "                 </tr> " +

                        "                   <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:30px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:22px'> " +
                        "                         <strong>"+formatearFecha(solicitudDescarga.getFechaRespuesta())+"</strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong> RESPUESTA DE SOLICITUD PARA DESCARGA DE DATOS </strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         <strong>Estimado/a:</strong>&nbsp;"+solicitudDescarga.getSolicitudDescarga().getNombre()+" "+ solicitudDescarga.getSolicitudDescarga().getApellido()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:20px;text-align:left;line-height:16px'> " +
                        "                         Su solicitud para descargar datos del proyeco: <strong>"+solicitudDescarga.getSolicitudDescarga().getProyectoInvestigacion().getNombreProyecto()+"</strong>, a sido: </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:0px;padding-right:30px;padding-left:30px;padding-bottom:0px;text-align:center;line-height:22px'> " +
                        "                         <strong> RECHAZADA </strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:10px;text-align:left;line-height:16px'> " +
                        "                         <strong>Motivo del rechazo:</strong>&nbsp;"+solicitudDescarga.getRespuesta()+" " +
                        "                     </td> " +
                        "                 </tr> " +
                        "                 <tr> " +
                        "                     <td align='left' bgcolor='#ffffff' " +
                        "                         style='font-family:Arial,sans-serif;color:#333333;font-size:12px;padding-top:20px;padding-right:30px;padding-left:30px;padding-bottom:40px;text-align:left;line-height:16px'> Se adjunta el archivo con los datos del proyecto. Gracias por utilizar nuestro servicio, estaremos gustosos de servirle.<br> <br> Atentamente,<br> <strong> " +
                        "                             <span class='il'>EcoAndes</span></strong> " +
                        "                     </td> " +
                        "                 </tr> " +
                        "             </tbody> " +
                        "         </table> " +
                        "     </td> " +
                                
                        "    </tr> " +
                        "    </tbody> " +
                        "    </table> " +
                        "    </td> " +
                        "    </tr> " +
                        "    </tbody> " +
                        "     </div> " +        
                        " </body> " +
                        " </html>");
        
        return mailBody.toString();
    }
    
    public static String formatearFecha(Date fecha) {
        SimpleDateFormat formatoDeseado = new SimpleDateFormat("EEEE, dd 'DE' MMMM 'DE' yyyy HH:mm", new Locale("es", "ES"));
        return formatoDeseado.format(fecha);
    }
    
    public String getDestinatario() {
        return destinatario;
    }
    
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
    public String getAsunto() {
        return asunto;
    }
    
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
