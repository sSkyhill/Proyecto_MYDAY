namespace MYDAY
{
    partial class Registro
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
            lblRegistro = new Label();
            txtUsuario = new TextBox();
            txtContrasena = new TextBox();
            txtMail = new TextBox();
            btnRegistro = new Button();
            SuspendLayout();
            // 
            // lblRegistro
            // 
            lblRegistro.AutoSize = true;
            lblRegistro.Font = new Font("Microsoft JhengHei", 22F, FontStyle.Bold, GraphicsUnit.Point, 0);
            lblRegistro.ForeColor = SystemColors.GradientInactiveCaption;
            lblRegistro.Location = new Point(215, 54);
            lblRegistro.Name = "lblRegistro";
            lblRegistro.Size = new Size(204, 47);
            lblRegistro.TabIndex = 0;
            lblRegistro.Text = "REGISTRO";
            lblRegistro.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // txtUsuario
            // 
            txtUsuario.BackColor = SystemColors.WindowFrame;
            txtUsuario.ForeColor = Color.White;
            txtUsuario.Location = new Point(233, 126);
            txtUsuario.Name = "txtUsuario";
            txtUsuario.Size = new Size(158, 27);
            txtUsuario.TabIndex = 1;
            txtUsuario.Tag = "";
            txtUsuario.Text = "Nombre de usuario";
            txtUsuario.Click += borrarTextoPlaceholder;
            // 
            // txtContrasena
            // 
            txtContrasena.BackColor = SystemColors.WindowFrame;
            txtContrasena.ForeColor = SystemColors.Window;
            txtContrasena.Location = new Point(233, 177);
            txtContrasena.Name = "txtContrasena";
            txtContrasena.Size = new Size(158, 27);
            txtContrasena.TabIndex = 2;
            txtContrasena.Text = "Contraseña";
            txtContrasena.Click += borrarTextoPlaceholder;
            // 
            // txtMail
            // 
            txtMail.BackColor = SystemColors.WindowFrame;
            txtMail.ForeColor = SystemColors.Window;
            txtMail.Location = new Point(233, 227);
            txtMail.Name = "txtMail";
            txtMail.Size = new Size(158, 27);
            txtMail.TabIndex = 3;
            txtMail.Text = "E-Mail";
            txtMail.Click += borrarTextoPlaceholder;
            txtMail.TextChanged += textBox3_TextChanged;
            // 
            // btnRegistro
            // 
            btnRegistro.BackColor = Color.PowderBlue;
            btnRegistro.Location = new Point(260, 288);
            btnRegistro.Name = "btnRegistro";
            btnRegistro.Size = new Size(94, 29);
            btnRegistro.TabIndex = 4;
            btnRegistro.Text = "Registrarse";
            btnRegistro.UseVisualStyleBackColor = false;
            btnRegistro.Click += btnRegistro_Click;
            // 
            // Registro
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(64, 64, 64);
            ClientSize = new Size(649, 450);
            Controls.Add(btnRegistro);
            Controls.Add(txtMail);
            Controls.Add(txtContrasena);
            Controls.Add(txtUsuario);
            Controls.Add(lblRegistro);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            MaximizeBox = false;
            Name = "Registro";
            Text = "Registrarse";
            Load += Registro_Load;
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label lblRegistro;
        private TextBox txtUsuario;
        private TextBox txtContrasena;
        private TextBox txtMail;
        private Button btnRegistro;
    }
}