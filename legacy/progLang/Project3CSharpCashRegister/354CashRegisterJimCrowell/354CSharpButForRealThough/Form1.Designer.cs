
namespace _354CSharpButForRealThough
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.tbItemEntry = new System.Windows.Forms.TextBox();
            this.tbItemQuantity = new System.Windows.Forms.TextBox();
            this.tbItemPrice = new System.Windows.Forms.TextBox();
            this.btnSave = new System.Windows.Forms.Button();
            this.btnNewCustomer = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.listTotal = new System.Windows.Forms.ListView();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.listAllItems = new System.Windows.Forms.ListBox();
            this.label6 = new System.Windows.Forms.Label();
            this.listQuantity = new System.Windows.Forms.ListView();
            this.label7 = new System.Windows.Forms.Label();
            this.listTaxOut = new System.Windows.Forms.ListView();
            this.SuspendLayout();
            // 
            // tbItemEntry
            // 
            this.tbItemEntry.Location = new System.Drawing.Point(12, 39);
            this.tbItemEntry.MaxLength = 250;
            this.tbItemEntry.Name = "tbItemEntry";
            this.tbItemEntry.Size = new System.Drawing.Size(517, 31);
            this.tbItemEntry.TabIndex = 0;
            // 
            // tbItemQuantity
            // 
            this.tbItemQuantity.Location = new System.Drawing.Point(12, 125);
            this.tbItemQuantity.Name = "tbItemQuantity";
            this.tbItemQuantity.Size = new System.Drawing.Size(101, 31);
            this.tbItemQuantity.TabIndex = 1;
            // 
            // tbItemPrice
            // 
            this.tbItemPrice.Location = new System.Drawing.Point(12, 207);
            this.tbItemPrice.Name = "tbItemPrice";
            this.tbItemPrice.Size = new System.Drawing.Size(101, 31);
            this.tbItemPrice.TabIndex = 2;
            // 
            // btnSave
            // 
            this.btnSave.Location = new System.Drawing.Point(12, 347);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(131, 39);
            this.btnSave.TabIndex = 3;
            this.btnSave.Text = "Save";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnNewCustomer
            // 
            this.btnNewCustomer.Location = new System.Drawing.Point(12, 399);
            this.btnNewCustomer.Name = "btnNewCustomer";
            this.btnNewCustomer.Size = new System.Drawing.Size(131, 39);
            this.btnNewCustomer.TabIndex = 4;
            this.btnNewCustomer.Text = "Clear";
            this.btnNewCustomer.UseVisualStyleBackColor = true;
            this.btnNewCustomer.Click += new System.EventHandler(this.btnNewCustomer_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 11);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(100, 25);
            this.label1.TabIndex = 5;
            this.label1.Text = "Item Name";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 97);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(80, 25);
            this.label2.TabIndex = 6;
            this.label2.Text = "Quantity";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 179);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(49, 25);
            this.label3.TabIndex = 7;
            this.label3.Text = "Price";
            // 
            // listTotal
            // 
            this.listTotal.HideSelection = false;
            this.listTotal.Location = new System.Drawing.Point(187, 399);
            this.listTotal.Name = "listTotal";
            this.listTotal.Size = new System.Drawing.Size(134, 36);
            this.listTotal.TabIndex = 9;
            this.listTotal.UseCompatibleStateImageBehavior = false;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(187, 97);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(79, 25);
            this.label4.TabIndex = 10;
            this.label4.Text = "Item List";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(208, 371);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(90, 25);
            this.label5.TabIndex = 11;
            this.label5.Text = "Total Cost";
            // 
            // listAllItems
            // 
            this.listAllItems.FormattingEnabled = true;
            this.listAllItems.ItemHeight = 25;
            this.listAllItems.Location = new System.Drawing.Point(187, 125);
            this.listAllItems.Name = "listAllItems";
            this.listAllItems.Size = new System.Drawing.Size(307, 229);
            this.listAllItems.TabIndex = 12;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(361, 371);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(122, 25);
            this.label6.TabIndex = 13;
            this.label6.Text = "Total Quantity";
            // 
            // listQuantity
            // 
            this.listQuantity.HideSelection = false;
            this.listQuantity.Location = new System.Drawing.Point(357, 399);
            this.listQuantity.Name = "listQuantity";
            this.listQuantity.Size = new System.Drawing.Size(126, 36);
            this.listQuantity.TabIndex = 14;
            this.listQuantity.UseCompatibleStateImageBehavior = false;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(550, 374);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(81, 25);
            this.label7.TabIndex = 15;
            this.label7.Text = "Sales Tax";
            // 
            // listTaxOut
            // 
            this.listTaxOut.HideSelection = false;
            this.listTaxOut.Location = new System.Drawing.Point(519, 402);
            this.listTaxOut.Name = "listTaxOut";
            this.listTaxOut.Size = new System.Drawing.Size(136, 36);
            this.listTaxOut.TabIndex = 16;
            this.listTaxOut.UseCompatibleStateImageBehavior = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(667, 457);
            this.Controls.Add(this.listTaxOut);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.listQuantity);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.listAllItems);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.listTotal);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnNewCustomer);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.tbItemPrice);
            this.Controls.Add(this.tbItemQuantity);
            this.Controls.Add(this.tbItemEntry);
            this.Name = "Form1";
            this.Text = "Shopping";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox tbItemEntry;
        private System.Windows.Forms.TextBox tbItemQuantity;
        private System.Windows.Forms.TextBox tbItemPrice;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnNewCustomer;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ListView listTotal;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.ListBox listAllItems;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ListView listQuantity;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.ListView listTaxOut;
    }
}

