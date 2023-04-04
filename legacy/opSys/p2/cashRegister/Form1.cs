using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace cashRegister
{
    public partial class Form1 : Form
    {
        decimal total, subtot, salesTax;
        int totItems;
        public Form1()
        {
            InitializeComponent();
        }
        private void addItemBtn_Click(object sender, EventArgs e)
        {
            //Getting the information
            String item = itemName.Text;
            int quantity = Convert.ToInt32(Math.Round(Decimal.Parse(quantityNum.Text)));
            decimal price = Math.Round(Decimal.Parse(priceBox.Text), 2, MidpointRounding.AwayFromZero);
            String[] row = { item, quantity.ToString(), (price * quantity).ToString("$####.00") };
            checkoutGrid.Rows.Add(row); //Add the item
            itemName.Text = quantityNum.Text = priceBox.Text = ""; //Clear the form
            //Update the totals
            totItems += quantity;
            salesTax += Math.Round((price * quantity) * (decimal)0.06, 2, MidpointRounding.AwayFromZero);
            subtot += (price * quantity);
            total = subtot + salesTax;
            //Update the labels
            totalLabel.Text = total.ToString("####.00");
            totItemLabel.Text = "Total items: " + totItems.ToString();
            subtotalLabel.Text = "Subtotal: " + subtot.ToString();
            taxLabel.Text = "Taxes: " + salesTax;
        }
        private void payBtn_Click(object sender, EventArgs e)
        {
            checkoutGrid.Rows.Clear();
            checkoutGrid.Refresh();
            total = 0;
        }
    }
}
