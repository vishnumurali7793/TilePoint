package com.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;
import com.util.NumtoString;

public class ReportAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SalesBaseBean salesBaseBean;
	private List<SalesBaseBean> salbeanList;
	private List<SalesDetailsBean> saldetList;
	private SalesAmountBean samtbean;
	private SalesHibernateDao salesHibernateDao = new SalesHibernateDao();
	String documentContentType;
	String documentFormat;
	String fileName;
	FileInputStream fileInputStream;
	DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

	public String generateSalesReport() throws DocumentException, IOException {
		ServletContext servletContext = ServletActionContext.getServletContext();
		String fileLocation;
		String destinationPath = null;
		File destination;
		ByteArrayOutputStream baos = null;
		FileOutputStream fos = null;
		String temp = "";

		if (salesBaseBean.getSalesId() != null) {
			SalesBaseBean sbb = new SalesBaseBean();
			sbb = salesHibernateDao.getsalesbase(salesBaseBean.getSalesId());
			saldetList = salesHibernateDao.getsalesDetailsList(salesBaseBean.getSalesId());
			samtbean = salesHibernateDao.getsalestotamt(salesBaseBean.getSalesId());
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
				document.addTitle("Download invoice " + sbb.getInvoiceNo());
				document.open();
				document.newPage();
				document.setMargins(5, 5, 5, 5);

				Chunk chunk;
				PdfPTable table;
				PdfPCell cell;
				Phrase phrase;
				Paragraph paragraph;

				paragraph = new Paragraph();
				temp = "TAX INVOICE";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 12, Font.UNDERLINE));
				paragraph.setAlignment(Element.ALIGN_CENTER);
				paragraph.setSpacingBefore(10);
				paragraph.add(chunk);
				document.add(paragraph);

				table = new PdfPTable(3);
				table.setWidths(new int[] { 30, 50, 20 });
				table.setWidthPercentage(100);

				cell = new PdfPCell(
						new Phrase("GSTIN : 32BRVPR2150KIZE", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(15);
				cell.setBorder(0);
				table.addCell(cell);

				phrase = new Phrase(
						new Chunk("TILE POINT", new Font(FontFamily.TIMES_ROMAN, 25, Element.ALIGN_CENTER)));
				cell.addElement(phrase);
				phrase = new Phrase(new Chunk("Kurumkutty, Parassala P.O.-695122",
						new Font(FontFamily.TIMES_ROMAN, 10, Element.ALIGN_LEFT)));
				cell.addElement(phrase);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingLeft(30);
				cell.setBorder(0);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Cell : 6383087919 \n8089004800 \n9486421383",
						new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingTop(15);
				cell.setBorder(0);
				table.addCell(cell);
				document.add(table);

				paragraph = new Paragraph();
				temp = "Date :";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				paragraph.add(chunk);
				temp = dateformat.format(sbb.getInvoiceDate());
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				paragraph.add(chunk);
				paragraph.setAlignment(Element.ALIGN_RIGHT);
				document.add(paragraph);

				table = new PdfPTable(6);
				table.setWidths(new float[] { 5f, 50f, 10f, 10f, 12.5f, 12.5f });
				table.setWidthPercentage(100);
				table.setSpacingBefore(20);

				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				cell.setFixedHeight(80);

				PdfPTable customerDetailsTable = new PdfPTable(3);
				PdfPCell customerDetailsCell;
				customerDetailsTable.setWidthPercentage(100);
				customerDetailsTable.setWidths(new float[] { 30f, 5f, 65f });

				customerDetailsCell = new PdfPCell(
						new Phrase("Bill to Party", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setColspan(3);
				customerDetailsCell.setBorder(0);
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase("Customer name", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsCell.setPaddingLeft(20);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				temp = sbb.getCustomerId().getCustomerName();
				customerDetailsCell = new PdfPCell(
						new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase("Customer address", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsCell.setPaddingLeft(20);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				temp = sbb.getCustomerId().getAddress1() + " " + sbb.getCustomerId().getAddress2();
				customerDetailsCell = new PdfPCell(
						new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase("Customer contact", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsCell.setPaddingLeft(20);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				temp = sbb.getCustomerId().getContact();
				customerDetailsCell = new PdfPCell(
						new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase("State, PIN code", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsCell.setPaddingLeft(20);
				customerDetailsTable.addCell(customerDetailsCell);

				customerDetailsCell = new PdfPCell(
						new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				temp = sbb.getCustomerId().getState() + ", " + sbb.getCustomerId().getPin();
				customerDetailsCell = new PdfPCell(
						new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				customerDetailsCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				customerDetailsCell.setBorder(0);
				customerDetailsTable.addCell(customerDetailsCell);

				cell.addElement(customerDetailsTable);
				table.addCell(cell);

				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setColspan(3);
				cell.setFixedHeight(80);

				PdfPTable insideTable = new PdfPTable(3);
				PdfPCell inlineCell;
				insideTable.setWidths(new float[] { 40f, 5f, 55f });
				insideTable.setWidthPercentage(100);

				inlineCell = new PdfPCell(
						new Phrase("Invoice No.", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				temp = sbb.getInvoiceNo();
				inlineCell = new PdfPCell(new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(
						new Phrase("Vehicle No.", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				temp = samtbean.getVehicleno();
				inlineCell = new PdfPCell(new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(
						new Phrase("Date of Supply", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				temp = dateformat.format(sbb.getInvoiceDate());
				inlineCell = new PdfPCell(new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(
						new Phrase("Place of Supply", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				temp = sbb.getPlaceToSupply();
				inlineCell = new PdfPCell(new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setPaddingTop(6);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(
						new Phrase("State of Supply", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(Rectangle.BOTTOM);
				inlineCell.setBorder(0);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				inlineCell = new PdfPCell(new Phrase(":", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				inlineCell.setBorder(0);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);

				temp = sbb.getStateToSupply();
				inlineCell = new PdfPCell(new Phrase(temp, new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				inlineCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				inlineCell.setBorder(0);
				inlineCell.setPaddingTop(5);
				insideTable.addCell(inlineCell);
				cell.addElement(insideTable);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("#", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(
						new Phrase("Product Description", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("HSN Code", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Qty", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Rate", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("Amount", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				int slNo = 1;

				for (SalesDetailsBean sb : saldetList) {

					cell = new PdfPCell(
							new Phrase(String.valueOf(slNo), new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(sb.getProductId().getProductName(),
							new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(sb.getHsnCode().toString(),
							new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(sb.getQuantity().toString(),
							new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(sb.getRate().toString(),
							new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase(sb.getTotalamount().toString(),
							new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
					cell.setPaddingTop(5);
					table.addCell(cell);

					slNo++;
				}

				/*
				 * adjust cellPadding depending upon the product listSize by setting different
				 * values for cellTopPadding
				 */
				int cellTopPadding = (salbeanList!=null && salbeanList.size() > 10) ? 100 : 200;

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(" ", new Font(FontFamily.HELVETICA, 8, Element.ALIGN_CENTER)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
				cell.setPaddingTop(cellTopPadding);
				table.addCell(cell);

				cell = new PdfPCell();
				cell.setColspan(3);
				cell.setFixedHeight(100); // for outer cell

				temp = "Total invoice amount in words,";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				cell.addElement(chunk);
				temp = NumtoString.convertAmountToWord(samtbean.getGrossamount());
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.BOLDITALIC));
				cell.addElement(chunk);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell();
				cell.setColspan(3);

				PdfPTable amtTable = new PdfPTable(2);
				amtTable.setWidthPercentage(100);
				amtTable.setWidths(new int[] { 70, 30 });
				PdfPCell amtHeadCell;

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Total Amount Before Tax", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = samtbean.getNetamount().toString();
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Add: CGST @ %", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = (samtbean.getCgst() != null) ? samtbean.getCgst().toString() : "0.0";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Add: SGST @ %", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = (samtbean.getSgst() != null) ? samtbean.getSgst().toString() : "0.0";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Add: IGST  @ %", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();

				temp = (samtbean.getIgst() != null) ? samtbean.getIgst().toString() : "0.0";

				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Vehicle charge", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = (samtbean.getVehicleamount() != null) ? samtbean.getVehicleamount().toString() : "0.0";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Loading charge", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = (samtbean.getLoadingcharge() != null) ? samtbean.getLoadingcharge().toString() : "0.0";
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.BOTTOM);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				chunk = new Chunk("Total Value", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(Rectangle.RIGHT);
				amtTable.addCell(amtHeadCell);

				amtHeadCell = new PdfPCell();
				temp = samtbean.getGrossamount().toString();
				chunk = new Chunk(temp, new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				amtHeadCell.addElement(chunk);
				amtHeadCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				amtHeadCell.setBorder(0);
				amtTable.addCell(amtHeadCell);
				cell.addElement(amtTable);
				table.addCell(cell);

				cell = new PdfPCell();
				cell.setColspan(3);
				cell.setFixedHeight(85);

				PdfPTable bankDetailTable = new PdfPTable(3);
				bankDetailTable.setWidthPercentage(100);
				bankDetailTable.setWidths(new int[] { 15, 2, 83 });
				PdfPCell bankDetailCell;

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("Bank Details", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setColspan(3);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("Bank Name", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk(":", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("ESAF", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("Account No", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk(":", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("10200000109677", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("Branch", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk(":", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("Parassala", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("IFSC Code", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk(":", new Font(FontFamily.HELVETICA, 8, Font.NORMAL));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_LEFT);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);

				bankDetailCell = new PdfPCell();
				chunk = new Chunk("ESMF0001300", new Font(FontFamily.HELVETICA, 8, Font.BOLD));
				bankDetailCell.addElement(chunk);
				bankDetailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				bankDetailCell.setBorder(0);
				bankDetailTable.addCell(bankDetailCell);
				cell.addElement(bankDetailTable);
				table.addCell(cell);

				cell = new PdfPCell();
				cell.setColspan(3);
				cell.setFixedHeight(60);

				PdfPTable signatureTable = new PdfPTable(1);
				signatureTable.setWidthPercentage(100);
				signatureTable.setHorizontalAlignment(Element.ALIGN_CENTER);
				PdfPCell signatureCell;

				signatureCell = new PdfPCell();
				signatureCell
						.addElement(new Chunk("For Tile Point,", new Font(FontFamily.HELVETICA, 8, Font.BOLDITALIC)));
				signatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				signatureCell.setBorder(0);
				signatureCell.setPaddingLeft(60);
				signatureTable.addCell(signatureCell);

				signatureCell = new PdfPCell();
				signatureCell
						.addElement(new Chunk("Authorized Signatory", new Font(FontFamily.HELVETICA, 8, Font.BOLD)));
				signatureCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				signatureCell.setPaddingTop(40);
				signatureCell.setPaddingLeft(50);
				signatureCell.setBorder(0);
				signatureTable.addCell(signatureCell);
				cell.addElement(signatureTable);
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

}
