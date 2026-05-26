namespace MYDAY
{
    partial class PanelPost
    {
        /// <summary> 
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary> 
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de componentes

        /// <summary> 
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            panel1 = new Panel();
            picPost = new PictureBox();
            lblComentario = new Label();
            panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)picPost).BeginInit();
            SuspendLayout();
            // 
            // panel1
            // 
            panel1.BackColor = SystemColors.ControlDarkDark;
            panel1.Controls.Add(picPost);
            panel1.Controls.Add(lblComentario);
            panel1.Dock = DockStyle.Fill;
            panel1.Location = new Point(0, 0);
            panel1.Name = "panel1";
            panel1.Size = new Size(333, 323);
            panel1.TabIndex = 0;
            // 
            // picPost
            // 
            picPost.Location = new Point(16, 14);
            picPost.Name = "picPost";
            picPost.Size = new Size(295, 228);
            picPost.SizeMode = PictureBoxSizeMode.StretchImage;
            picPost.TabIndex = 1;
            picPost.TabStop = false;
            // 
            // lblComentario
            // 
            lblComentario.Location = new Point(28, 262);
            lblComentario.Name = "lblComentario";
            lblComentario.Size = new Size(50, 20);
            lblComentario.TabIndex = 0;
            lblComentario.Text = "label1";
            // 
            // PanelPost
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            Controls.Add(panel1);
            Name = "PanelPost";
            Size = new Size(333, 323);
            panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)picPost).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private Panel panel1;
        private PictureBox picPost;
        private Label lblComentario;
    }
}
