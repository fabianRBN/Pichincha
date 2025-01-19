import pdfMake from "pdfmake/build/pdfmake";
import pdfFonts from "pdfmake/build/vfs_fonts";
import { variable64 } from "../assets/img";
import { AccountMovement } from "../core/interfaces/accountMovement.interface";


(pdfMake as any).vfs = pdfFonts.pdfMake.vfs;



const generatePDF = (
  movementsLis: AccountMovement[],
  reciboNo: string,
  fecha: string
) => {

  const tableBody = [
    [
      { text: "Fecha", style: "tableHeader" },
      { text: "Cliente", style: "tableHeader" },
      { text: "Numero Cuenta", style: "tableHeader" },
      { text: "Tipo", style: "tableHeader" },
      { text: "Saldo Inicial", style: "tableHeader" },
      { text: "Estado", style: "tableHeader" },
      { text: "Movimiento", style: "tableHeader" },
      { text: "Saldo Disponible", style: "tableHeader" },
    ],
    ...movementsLis.map((movement) => [
      movement.fecha,
      movement.cliente.toString(),
      movement.numeroCuenta,
      movement.tipo,
      movement.saldoInicial,
      movement.estado,
      movement.movimiento,
      `$ ${movement.saldoDisponible}`,
    ]),
  ];


  const totalGeneral = 0;


  const content: any[] = [];


  content.push({
    columns: [
      { image: variable64.miVar, width: 150 },
      {
        stack: [
          { text: `Reporte No. ${reciboNo}`, style: "header" },
          { text: `Fecha: ${fecha}`, style: "subheader" },
        ],
        alignment: "right",
      },
    ],
  });


  content.push({
    qr: 'https://fabianrbn.github.io/CV/',
    fit: 100,
    alignment: "right",
    margin: [0, 10, 0, 10],
  });


  content.push({ text: "\n" });


  content.push({
    table: {
      headerRows: 1,
      widths: ["*", "*", "*", "*", "*", "*", "*", "*"],
      body: tableBody,
    },
    layout: "lightHorizontalLines",
    margin: [0, 10, 0, 10],
  });


  content.push({
    columns: [
      { text: "", width: "*" },
      {
        text: `Total: $ ${totalGeneral}`,
        style: "total",
        alignment: "right",
        margin: [0, 10, 0, 10],
      },
    ],
  });


  const styles = {
    header: {
      fontSize: 14,
      bold: true,
    },
    subheader: {
      fontSize: 12,
      margin: [0, 5, 0, 5],
    },
    tableHeader: {
      bold: true,
      fontSize: 12,
      color: "black",
    },
    total: {
      fontSize: 12,
      bold: true,
    },
  };


  const docDefinition: any = {
    content,
    styles,
    pageOrientation: 'landscape',
  };

  pdfMake.createPdf(docDefinition).open();
};

export default generatePDF;