
namespace cashRegister
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle2 = new System.Windows.Forms.DataGridViewCellStyle();
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.checkoutGrid = new System.Windows.Forms.DataGridView();
            this.itemCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.quantityCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.priceCol = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.itemBox = new System.Windows.Forms.GroupBox();
            this.priceBox = new System.Windows.Forms.TextBox();
            this.quantityNum = new System.Windows.Forms.TextBox();
            this.itemName = new System.Windows.Forms.TextBox();
            this.quantityLabel = new System.Windows.Forms.Label();
            this.priceLabel = new System.Windows.Forms.Label();
            this.itemLabel = new System.Windows.Forms.Label();
            this.addItemBtn = new System.Windows.Forms.Button();
            this.totalLabel = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.payBtn = new System.Windows.Forms.Button();
            this.totItemLabel = new System.Windows.Forms.Label();
            this.subtotalLabel = new System.Windows.Forms.Label();
            this.taxLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.checkoutGrid)).BeginInit();
            this.itemBox.SuspendLayout();
            this.SuspendLayout();
            // 
            // checkoutGrid
            // 
            this.checkoutGrid.AllowUserToAddRows = false;
            this.checkoutGrid.AllowUserToDeleteRows = false;
            this.checkoutGrid.AllowUserToResizeColumns = false;
            this.checkoutGrid.AllowUserToResizeRows = false;
            this.checkoutGrid.BackgroundColor = System.Drawing.Color.WhiteSmoke;
            this.checkoutGrid.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.checkoutGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.checkoutGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.itemCol,
            this.quantityCol,
            this.priceCol});
            dataGridViewCellStyle2.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle2.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            dataGridViewCellStyle2.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle2.NullValue = null;
            dataGridViewCellStyle2.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle2.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle2.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.checkoutGrid.DefaultCellStyle = dataGridViewCellStyle2;
            this.checkoutGrid.Location = new System.Drawing.Point(241, 25);
            this.checkoutGrid.MaximumSize = new System.Drawing.Size(454, 300);
            this.checkoutGrid.Name = "checkoutGrid";
            this.checkoutGrid.ReadOnly = true;
            this.checkoutGrid.RowHeadersVisible = false;
            this.checkoutGrid.RowHeadersWidth = 20;
            this.checkoutGrid.RowTemplate.Height = 25;
            this.checkoutGrid.RowTemplate.ReadOnly = true;
            this.checkoutGrid.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            this.checkoutGrid.Size = new System.Drawing.Size(454, 300);
            this.checkoutGrid.TabIndex = 0;
            // 
            // itemCol
            // 
            this.itemCol.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.None;
            this.itemCol.HeaderText = "Items";
            this.itemCol.MinimumWidth = 6;
            this.itemCol.Name = "itemCol";
            this.itemCol.ReadOnly = true;
            this.itemCol.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.itemCol.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            this.itemCol.Width = 120;
            // 
            // quantityCol
            // 
            this.quantityCol.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.None;
            this.quantityCol.HeaderText = "Quantity";
            this.quantityCol.MinimumWidth = 6;
            this.quantityCol.Name = "quantityCol";
            this.quantityCol.ReadOnly = true;
            this.quantityCol.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.quantityCol.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            this.quantityCol.Width = 120;
            // 
            // priceCol
            // 
            this.priceCol.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.None;
            dataGridViewCellStyle1.Format = "C2";
            dataGridViewCellStyle1.NullValue = null;
            this.priceCol.DefaultCellStyle = dataGridViewCellStyle1;
            this.priceCol.HeaderText = "Price";
            this.priceCol.MinimumWidth = 6;
            this.priceCol.Name = "priceCol";
            this.priceCol.ReadOnly = true;
            this.priceCol.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.priceCol.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            this.priceCol.Width = 120;
            // 
            // itemBox
            // 
            this.itemBox.Controls.Add(this.priceBox);
            this.itemBox.Controls.Add(this.quantityNum);
            this.itemBox.Controls.Add(this.itemName);
            this.itemBox.Controls.Add(this.quantityLabel);
            this.itemBox.Controls.Add(this.priceLabel);
            this.itemBox.Controls.Add(this.itemLabel);
            this.itemBox.Controls.Add(this.addItemBtn);
            this.itemBox.Location = new System.Drawing.Point(12, 25);
            this.itemBox.Name = "itemBox";
            this.itemBox.Size = new System.Drawing.Size(223, 300);
            this.itemBox.TabIndex = 9;
            this.itemBox.TabStop = false;
            this.itemBox.Text = "Checkout Item";
            // 
            // priceBox
            // 
            this.priceBox.Location = new System.Drawing.Point(100, 174);
            this.priceBox.Name = "priceBox";
            this.priceBox.Size = new System.Drawing.Size(110, 22);
            this.priceBox.TabIndex = 6;
            // 
            // quantityNum
            // 
            this.quantityNum.Location = new System.Drawing.Point(100, 113);
            this.quantityNum.Name = "quantityNum";
            this.quantityNum.Size = new System.Drawing.Size(110, 22);
            this.quantityNum.TabIndex = 5;
            // 
            // itemName
            // 
            this.itemName.Location = new System.Drawing.Point(100, 54);
            this.itemName.Name = "itemName";
            this.itemName.Size = new System.Drawing.Size(110, 22);
            this.itemName.TabIndex = 4;
            // 
            // quantityLabel
            // 
            this.quantityLabel.AutoSize = true;
            this.quantityLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.quantityLabel.Location = new System.Drawing.Point(15, 113);
            this.quantityLabel.Name = "quantityLabel";
            this.quantityLabel.Size = new System.Drawing.Size(71, 20);
            this.quantityLabel.TabIndex = 2;
            this.quantityLabel.Text = "Quantity";
            // 
            // priceLabel
            // 
            this.priceLabel.AutoSize = true;
            this.priceLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.priceLabel.Location = new System.Drawing.Point(15, 174);
            this.priceLabel.Name = "priceLabel";
            this.priceLabel.Size = new System.Drawing.Size(48, 20);
            this.priceLabel.TabIndex = 3;
            this.priceLabel.Text = "Price";
            // 
            // itemLabel
            // 
            this.itemLabel.AutoSize = true;
            this.itemLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.itemLabel.Location = new System.Drawing.Point(15, 54);
            this.itemLabel.Name = "itemLabel";
            this.itemLabel.Size = new System.Drawing.Size(41, 20);
            this.itemLabel.TabIndex = 1;
            this.itemLabel.Text = "Item";
            // 
            // addItemBtn
            // 
            this.addItemBtn.Location = new System.Drawing.Point(74, 232);
            this.addItemBtn.Margin = new System.Windows.Forms.Padding(2);
            this.addItemBtn.Name = "addItemBtn";
            this.addItemBtn.Size = new System.Drawing.Size(75, 32);
            this.addItemBtn.TabIndex = 0;
            this.addItemBtn.Text = "Add Item";
            this.addItemBtn.UseVisualStyleBackColor = true;
            this.addItemBtn.Click += new System.EventHandler(this.addItemBtn_Click);
            // 
            // totalLabel
            // 
            this.totalLabel.AutoSize = true;
            this.totalLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F);
            this.totalLabel.Location = new System.Drawing.Point(480, 365);
            this.totalLabel.Name = "totalLabel";
            this.totalLabel.Size = new System.Drawing.Size(19, 29);
            this.totalLabel.TabIndex = 8;
            this.totalLabel.Text = " ";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F);
            this.label1.Location = new System.Drawing.Point(388, 365);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(93, 29);
            this.label1.TabIndex = 7;
            this.label1.Text = "Total  $";
            // 
            // payBtn
            // 
            this.payBtn.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.payBtn.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            this.payBtn.FlatAppearance.BorderSize = 0;
            this.payBtn.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.payBtn.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.payBtn.ForeColor = System.Drawing.SystemColors.ControlText;
            this.payBtn.Location = new System.Drawing.Point(265, 360);
            this.payBtn.Name = "payBtn";
            this.payBtn.Size = new System.Drawing.Size(80, 40);
            this.payBtn.TabIndex = 11;
            this.payBtn.Text = "Pay";
            this.payBtn.UseVisualStyleBackColor = false;
            this.payBtn.Click += new System.EventHandler(this.payBtn_Click);
            // 
            // totItemLabel
            // 
            this.totItemLabel.AutoSize = true;
            this.totItemLabel.Location = new System.Drawing.Point(241, 328);
            this.totItemLabel.Name = "totItemLabel";
            this.totItemLabel.Size = new System.Drawing.Size(81, 17);
            this.totItemLabel.TabIndex = 12;
            this.totItemLabel.Text = "Total items:";
            // 
            // subtotalLabel
            // 
            this.subtotalLabel.AutoSize = true;
            this.subtotalLabel.Location = new System.Drawing.Point(406, 328);
            this.subtotalLabel.Name = "subtotalLabel";
            this.subtotalLabel.Size = new System.Drawing.Size(64, 17);
            this.subtotalLabel.TabIndex = 13;
            this.subtotalLabel.Text = "Subtotal:";
            // 
            // taxLabel
            // 
            this.taxLabel.AutoSize = true;
            this.taxLabel.Location = new System.Drawing.Point(558, 328);
            this.taxLabel.Name = "taxLabel";
            this.taxLabel.Size = new System.Drawing.Size(50, 17);
            this.taxLabel.TabIndex = 14;
            this.taxLabel.Text = "Taxes:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(707, 403);
            this.Controls.Add(this.taxLabel);
            this.Controls.Add(this.subtotalLabel);
            this.Controls.Add(this.totItemLabel);
            this.Controls.Add(this.payBtn);
            this.Controls.Add(this.checkoutGrid);
            this.Controls.Add(this.itemBox);
            this.Controls.Add(this.totalLabel);
            this.Controls.Add(this.label1);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.MinimumSize = new System.Drawing.Size(725, 450);
            this.Name = "Form1";
            this.Text = "Cash Register";
            ((System.ComponentModel.ISupportInitialize)(this.checkoutGrid)).EndInit();
            this.itemBox.ResumeLayout(false);
            this.itemBox.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView checkoutGrid;
        private System.Windows.Forms.GroupBox itemBox;
        private System.Windows.Forms.TextBox priceBox;
        private System.Windows.Forms.TextBox quantityNum;
        private System.Windows.Forms.TextBox itemName;
        private System.Windows.Forms.Label quantityLabel;
        private System.Windows.Forms.Label priceLabel;
        private System.Windows.Forms.Label itemLabel;
        private System.Windows.Forms.Button addItemBtn;
        private System.Windows.Forms.Label totalLabel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button payBtn;
        private System.Windows.Forms.DataGridViewTextBoxColumn itemCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn quantityCol;
        private System.Windows.Forms.DataGridViewTextBoxColumn priceCol;
        private System.Windows.Forms.Label totItemLabel;
        private System.Windows.Forms.Label subtotalLabel;
        private System.Windows.Forms.Label taxLabel;
    }
}

