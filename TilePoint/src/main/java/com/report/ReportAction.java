package com.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Text;
import org.hibernate.mapping.Table;

import com.entities.SalesAmountBean;
import com.entities.SalesBaseBean;
import com.entities.SalesDetailsBean;
import com.hibernatedao.SalesHibernateDao;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SalesBaseBean salesBaseBean;
	private List<SalesBaseBean> salbeanList;
	private List<SalesDetailsBean> saldetList;
	private SalesAmountBean samtbean;
	private SalesHibernateDao salesHibernateDao = new SalesHibernateDao();
	String despath = "./WEB-INF/downloads/pdf/";
	String documentContentType;
	String documentFormat;
	String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String generateSalesReport() throws DocumentException, IOException {
		ServletContext servletContext = ServletActionContext.getServletContext();
		String fileLocation;
		String destinationPath = null;
		File destination;
		ByteArrayOutputStream baos = null;
		FileOutputStream fos = null;
		FileInputStream fileInputStream;
		String temp = "";

		if (salesBaseBean.getSalesId() != null) {
			SalesBaseBean sbb=new SalesBaseBean();
			sbb=salesHibernateDao.getsalesbase(salesBaseBean.getSalesId());
			saldetList=salesHibernateDao.getsalesDetailsList(salesBaseBean.getSalesId());
			samtbean=salesHibernateDao.getsalestotamt(salesBaseBean.getSalesId());
			setDocumentContentType("application/pdf");
			setDocumentFormat("pdf");
			// *****LOCATION-OF-DOWNLOAD-FILE*****//
			fileLocation = "/uploads";
			destination = new File(servletContext.getRealPath(fileLocation));
			// *****IF THE FOLDER DOES NOT EXITS,CREATING IT
			if (!destination.exists()) {
				destination.mkdirs();
			}
			setFileName("TP" + sbb.getInvoiceNo().replace('/', '-') + "." + getDocumentFormat());
			destinationPath = destination.getAbsolutePath() + "/" + getFileName();

			try {
				baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4);
				PdfWriter.getInstance(document, baos);
				document.addTitle("SALES PAGE");
				document.open();
				document.newPage();
				document.setMargins(5, 5, 5, 5);
				
				Chunk chunk;
				PdfPTable table;
				PdfPCell cell;
				Phrase phrase;
				
				Font font=new Font(FontFamily.HELVETICA, 8);
				Paragraph paragraph = new Paragraph();
				table = new PdfPTable(3);
				table.setWidths(new int[] {40,25,35 });
				table.setTotalWidth(100);
				
				cell = new PdfPCell(new Paragraph("GSTIN:32BRVPR2150K1ZC"));
				//cell.setColspan(1);
				cell.setBorder(0);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph("TAX INVOICE"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell.setColspan(2);
				cell.setBorder(0);
				table.addCell(cell);

				cell = new PdfPCell(
						new Paragraph("CONTACT:6383087919" + "\n" + "                  8089004800" + "\n" + "                  9486421383"));
				//cell.setColspan(3);
				cell.setBorder(0);
				table.addCell(cell);

				document.add(table);
				document.close();
				fos = new FileOutputStream(destinationPath);
				fos.write(baos.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null)
					fos.close();
				if (baos != null)
					baos.close();
			}
			fileInputStream = new FileInputStream(new File(destinationPath));
		}
		return SUCCESS;
	}

	public SalesBaseBean getSalesBaseBean() {
		return salesBaseBean;
	}

	public void setSalesBaseBean(SalesBaseBean salesBaseBean) {
		this.salesBaseBean = salesBaseBean;
	}

	public List<SalesBaseBean> getSalbeanList() {
		return salbeanList;
	}

	public void setSalbeanList(List<SalesBaseBean> salbeanList) {
		this.salbeanList = salbeanList;
	}

	public String getDocumentContentType() {
		return documentContentType;
	}

	public void setDocumentContentType(String documentContentType) {
		this.documentContentType = documentContentType;
	}

	public String getDocumentFormat() {
		return documentFormat;
	}

	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}

	public List<SalesDetailsBean> getSaldetList() {
		return saldetList;
	}

	public void setSaldetList(List<SalesDetailsBean> saldetList) {
		this.saldetList = saldetList;
	}

	public SalesAmountBean getSamtbean() {
		return samtbean;
	}

	public void setSamtbean(SalesAmountBean samtbean) {
		this.samtbean = samtbean;
	}

}
