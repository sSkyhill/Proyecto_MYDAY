namespace MYDAY
{
    partial class DatosPerfil
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
            pictureBox1 = new PictureBox();
            btnCambiarFoto = new Button();
            label1 = new Label();
            label2 = new Label();
            ((System.ComponentModel.ISupportInitialize)pictureBox1).BeginInit();
            SuspendLayout();
            // 
            // pictureBox1
            // 
            pictureBox1.Location = new Point(138, 12);
            pictureBox1.Name = "pictureBox1";
            pictureBox1.Size = new Size(112, 87);
            pictureBox1.TabIndex = 0;
            pictureBox1.TabStop = false;
            // 
            // btnCambiarFoto
            // 
            btnCambiarFoto.ForeColor = SystemColors.ActiveCaptionText;
            btnCambiarFoto.Location = new Point(256, 40);
            btnCambiarFoto.Name = "btnCambiarFoto";
            btnCambiarFoto.Size = new Size(94, 29);
            btnCambiarFoto.TabIndex = 1;
            btnCambiarFoto.Text = "Cambiar foto";
            btnCambiarFoto.UseVisualStyleBackColor = true;
            btnCambiarFoto.Click += btnCambiarFoto_Click;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(77, 127);
            label1.Name = "label1";
            label1.Size = new Size(125, 20);
            label1.TabIndex = 2;
            label1.Text = "Nombre Usuario: ";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(77, 161);
            label2.Name = "label2";
            label2.Size = new Size(55, 20);
            label2.TabIndex = 3;
            label2.Text = "E-Mail:";
            // 
            // DatosPerfil
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.WindowFrame;
            ClientSize = new Size(402, 223);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(btnCambiarFoto);
            Controls.Add(pictureBox1);
            ForeColor = SystemColors.ButtonHighlight;
            FormBorderStyle = FormBorderStyle.FixedDialog;
            MaximizeBox = false;
            Name = "DatosPerfil";
            Text = "Datos del usuario";
            ((System.ComponentModel.ISupportInitialize)pictureBox1).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private PictureBox pictureBox1;
        private Button btnCambiarFoto;
        private Label label1;
        private Label label2;
    }
}