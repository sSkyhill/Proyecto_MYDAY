namespace MYDAY
{
    partial class InicioSesion
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
            txtUsuario = new TextBox();
            txtContrasena = new TextBox();
            btnInicio = new Button();
            pctLogo = new PictureBox();
            lblRegistro = new Label();
            lblCuenta = new Label();
            ((System.ComponentModel.ISupportInitialize)pctLogo).BeginInit();
            SuspendLayout();
            // 
            // txtUsuario
            // 
            txtUsuario.BackColor = SystemColors.WindowFrame;
            txtUsuario.ForeColor = SystemColors.Window;
            txtUsuario.Location = new Point(216, 189);
            txtUsuario.Name = "txtUsuario";
            txtUsuario.Size = new Size(182, 27);
            txtUsuario.TabIndex = 1;
            txtUsuario.Text = "Nombre de usuario";
            txtUsuario.Click += BorrarTextoPlaceholder;
            txtUsuario.Enter += BorrarTextoPlaceholder;
            // 
            // txtContrasena
            // 
            txtContrasena.BackColor = SystemColors.WindowFrame;
            txtContrasena.ForeColor = SystemColors.Window;
            txtContrasena.Location = new Point(216, 250);
            txtContrasena.MaxLength = 250;
            txtContrasena.Name = "txtContrasena";
            txtContrasena.Size = new Size(182, 27);
            txtContrasena.TabIndex = 2;
            txtContrasena.Text = "Contraseña";
            txtContrasena.Click += BorrarTextoPlaceholder;
            txtContrasena.Enter += BorrarTextoPlaceholder;
            // 
            // btnInicio
            // 
            btnInicio.BackColor = Color.PowderBlue;
            btnInicio.FlatAppearance.BorderColor = Color.DimGray;
            btnInicio.FlatAppearance.MouseDownBackColor = Color.Transparent;
            btnInicio.FlatAppearance.MouseOverBackColor = Color.White;
            btnInicio.Location = new Point(249, 304);
            btnInicio.Name = "btnInicio";
            btnInicio.Size = new Size(108, 40);
            btnInicio.TabIndex = 3;
            btnInicio.Text = "Iniciar Sesión";
            btnInicio.UseVisualStyleBackColor = false;
            btnInicio.Click += btnInicio_Click;
            // 
            // pctLogo
            // 
            pctLogo.BackColor = Color.Transparent;
            pctLogo.BackgroundImage = Properties.Resources.Copilot_20260119_170732;
            pctLogo.BackgroundImageLayout = ImageLayout.Zoom;
            pctLogo.Image = Properties.Resources.Copilot_20260119_170732;
            pctLogo.Location = new Point(225, 50);
            pctLogo.Name = "pctLogo";
            pctLogo.Size = new Size(173, 133);
            pctLogo.TabIndex = 0;
            pctLogo.TabStop = false;
            // 
            // lblRegistro
            // 
            lblRegistro.AutoSize = true;
            lblRegistro.Cursor = Cursors.Hand;
            lblRegistro.ForeColor = SystemColors.ActiveCaption;
            lblRegistro.Location = new Point(304, 359);
            lblRegistro.Name = "lblRegistro";
            lblRegistro.Size = new Size(109, 20);
            lblRegistro.TabIndex = 3;
            lblRegistro.Text = "Regístrate aquí";
            lblRegistro.Click += lblRegistro_Click;
            // 
            // lblCuenta
            // 
            lblCuenta.AutoSize = true;
            lblCuenta.ForeColor = SystemColors.Control;
            lblCuenta.Location = new Point(164, 359);
            lblCuenta.Name = "lblCuenta";
            lblCuenta.Size = new Size(134, 20);
            lblCuenta.TabIndex = 0;
            lblCuenta.Text = "¿No tienes cuenta?";
            // 
            // InicioSesion
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(64, 64, 64);
            ClientSize = new Size(649, 450);
            Controls.Add(lblCuenta);
            Controls.Add(lblRegistro);
            Controls.Add(pctLogo);
            Controls.Add(btnInicio);
            Controls.Add(txtContrasena);
            Controls.Add(txtUsuario);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            MaximizeBox = false;
            Name = "InicioSesion";
            Text = "Inicio Sesion";
            Load += InicioSesion_Load;
            ((System.ComponentModel.ISupportInitialize)pctLogo).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox txtUsuario;
        private TextBox txtContrasena;
        private Button btnInicio;
        private PictureBox pctLogo;
        private Label lblRegistro;
        private Label lblCuenta;
    }
}
