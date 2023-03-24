using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _354CSharpButForRealThough
{

   
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private decimal salesTax = (decimal) .06;
        private int runningTally = 0;
        private decimal runningCost = (decimal)000.00;
        private decimal totalTax = 0;


        private void btnSave_Click(object sender, EventArgs e)
        {
            if (tbItemEntry.Text.Trim() == "" || tbItemQuantity.Text.Trim() == "" || tbItemPrice.Text.Trim() == "")
            {
                /*
                 * We weren't instructed to have any error handling, far as I can
                 * tell, and not looking to be a smartarse, but I legitimately 
                 * don't know C# well enough to even know how I'd handle a user
                 * hitting SAVE without all fields filled out without screwing
                 * everything up. If I have time, I'll see if I can figure out a 
                 * pop-up dialog with a big "OI, DUMB-DUMB!" on it
                 */

            }
            else
            {
                // I have absolutely no idea what I'm doing. : D
                string newName = tbItemEntry.Text;
                int newQuantity = Int32.Parse(tbItemQuantity.Text);
                decimal newCost = Decimal.Parse(tbItemPrice.Text);
                shoppingObject addition = new shoppingObject(newName, newQuantity, newCost);

                // Empty out entry fields for new stuff
                tbItemEntry.Clear();
                tbItemPrice.Clear();
                tbItemQuantity.Clear();

                // Adjust totals
                runningTally += newQuantity;

                // This math rounding stuff looks atrocious, but I wanted to be sure
                // it rounds somewhat realistically (that is, up, in all cases, for money)
                totalTax += Math.Round((newQuantity * newCost * salesTax), 2, MidpointRounding.AwayFromZero);
                runningCost += Math.Round((newCost * newQuantity), 2, MidpointRounding.AwayFromZero) + Math.Round((newQuantity * newCost * salesTax), 2, MidpointRounding.AwayFromZero);
                
                // Change OUT windows
                listAllItems.Items.Add(addition.ToString());
                listTotal.Clear();
                listTotal.Items.Add(runningCost.ToString("$####.00"));


                listQuantity.Clear();
                listQuantity.Items.Add(runningTally.ToString());

                listTaxOut.Clear();
                listTaxOut.Items.Add(totalTax.ToString("$####.00"));
            }
        }

        // Clear button wipes everything clean, resets variables
        private void btnNewCustomer_Click(object sender, EventArgs e)
        {
            listAllItems.Items.Clear();
            listTotal.Items.Clear();
            runningCost = 0;
            runningTally = 0;
            tbItemEntry.Clear();
            tbItemPrice.Clear();
            tbItemQuantity.Clear();
            totalTax = 0;
            listQuantity.Clear();
            listTaxOut.Clear();
        }
    }
    class shoppingObject
    {
        public String name;
        public decimal cost;
        public int quantity;

        public shoppingObject(String nameIn, int quanIn, decimal costIn)
        {
            name = nameIn;
            quantity = quanIn;
            cost = costIn;
        }

        public String ToString()
        {
            return name + "       " + cost + "       " + quantity;
        }
    }
}
