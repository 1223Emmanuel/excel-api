package com.eglobal.bo.api.zip.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eglobal.bo.api.zip.dto.CImagenesDTO;
import com.eglobal.bo.api.zip.dto.LBitacoraDTO;
import com.eglobal.bo.api.zip.dto.ParametrosDTO;
import com.eglobal.bo.api.zip.exceptions.RestException;
import com.eglobal.bo.api.zip.repository.dao.ConsultaDAO;
import com.eglobal.bo.api.zip.repository.dao.HtmlPlantillaDAO;
import com.eglobal.bo.api.zip.service.HtmlService;
import com.eglobal.bo.api.zip.utils.Constants;

import java.io.File;

@Service
public class HtmlServiceImpl implements HtmlService {

	private Logger logger = LoggerFactory.getLogger(HtmlServiceImpl.class);
	@Autowired
	private ConsultaDAO consultadao;

	@Autowired
	private HtmlPlantillaDAO htmlplantilla;

	public String obtenerParamhtml(ParametrosDTO bcom, List<CImagenesDTO> html) throws RestException {
		String html1 = " ";
		List<LBitacoraDTO> bitacora = consultadao.obtenerConsulta(bcom);

		List<List<LBitacoraDTO>> list = getPages(bitacora);
		for (List<LBitacoraDTO> list2 : list) {
			html = htmlplantilla.obtenerPlantilla(bcom);
			for (CImagenesDTO plantilla : html) {
				html1 += construyeReporte(list2, plantilla.getArarchivo());
			}
		}
		return html1;
	}

	private <T> List<List<T>> getPages(List<T> pagArray) {
		Collection<T> c = new ArrayList<>();
		c.addAll((Collection<? extends T>) pagArray);

		int pageSize = 5;

		List<T> list = new ArrayList<>(c);

		if (pageSize <= 0 || pageSize > list.size())
			pageSize = list.size();
		int numPages = (int) Math.ceil((double) list.size() / (double) pageSize);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<List<T>> pages = new ArrayList(numPages);
		for (int pageNum = 0; pageNum < numPages;)
			pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));

		return pages;
	}

	private String construyeReporte(List<LBitacoraDTO> bitacora, String html) throws RestException {
		String docHtml1 = null;
		try {
			Document doc1;
			doc1 = Jsoup.parse(html, "UTF-8");
			// función para poner acentos en el PDF
			doc1.head().append("<meta charset=\"ISO-8859-1\">");
			Elements els = doc1.body().getAllElements();
			for (Element e : els) {
				List<org.jsoup.nodes.TextNode> tnList = e.textNodes();
				for (org.jsoup.nodes.TextNode tn : tnList) {
					for (LBitacoraDTO bitacorahtml : bitacora) {
						// Menu Izuierdo
						tn.text(tn.text().replace("$[NOMBRE_NEG]", bitacorahtml.getCnegnombre()));
						tn.text(tn.text().replace("$[NOMBRE_CALLE]", bitacorahtml.getCnegcalle()));
						tn.text(tn.text().replace("$[NOMBRE_CIUDAD]", bitacorahtml.getCciunombre()));
						tn.text(tn.text().replace("$[NOMBRE_COLONIA]", bitacorahtml.getCnegcolonia()));
						tn.text(tn.text().replace("$[CODIGO_POSTAL]", bitacorahtml.getCnegcodpos()));
						tn.text(tn.text().replace("$[TELFONO_NEGOCIO]", bitacorahtml.getCnegnumtel()));
						tn.text(tn.text().replace("$[NOMBRE_RESPONSABLE]", bitacorahtml.getCnegnomres()));
						tn.text(tn.text().replace("$[C_NEG_PLAZO_ENT]", bitacorahtml.getCnegplazoent()));
						// Menu derecho Y LOgo

						SimpleDateFormat sdf = new SimpleDateFormat(" d 'de' MMMM 'de' yyyy", new Locale("ES", "MX"));
						String fechaComoCadena = sdf.format(new Date());
						tn.text(tn.text().replace("$[FECHA_ACTUAL]", fechaComoCadena));
						// Creacion de imagen
						byte[] bytes = bitacorahtml.getImgimagen();
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
						ImageIO.write(bufferedImage, "png", new File(Constants.RUTA));
						bufferedImage.flush();
					}
				}
			}

			// Colocacion de logo
			Element tableI = doc1.select("tbody").get(0);
			Elements rowI = tableI.select("tr");
			Element column = rowI.select("td").get(0);
			column.html("<img src=\'C:\\Users\\Emmanuel\\Documents\\wildfly-12.0.0.Final\\bin\\image.png'>");
			// Tabla detalle
			Element table = doc1.select("tbody").get(5);

			for (LBitacoraDTO s : bitacora) {
				// Obtiene fecha y la convierte en el formato especifico
				DateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");
				DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				Date date = inputFormat.parse((s).getFhvennat());
				String outputText = outputFormat.format(date);
				// tabla de Transacciones
				table.append("<tr>\r\n"
						+ "	<td style=\"text-align:center; width:18px\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getDpetnumcta() + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:6px\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ outputText + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:8px\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ "$" + s.getDpetimpotran() + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:8px\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getDpetnumaut() + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:14%\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getFoliobancario() + "</span></span></td>\r\n"
						+ " <td style=\"text-align:center; width:9%\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getDpetfolegl() + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:26%\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getCnegnombre() + "</span></span></td>\r\n"
						+ "	<td style=\"text-align:center; width:11%\"><span style=\"font-size:10px\"><span style=\"font-family:Arial,Helvetica,sans-serif\">"
						+ s.getCtipdocclave() + "</span></span></td>\r\n" + "	</tr>\r\n");
			}
			docHtml1 = doc1.html();
			return docHtml1;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new RestException("102", "Error en ServiceImplement");
		}

	}

}
