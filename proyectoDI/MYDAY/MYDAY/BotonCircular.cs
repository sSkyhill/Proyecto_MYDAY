using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Net.Mime.MediaTypeNames;

namespace MYDAY
{
    public partial class BotonCircular : UserControl
    {
        public BotonCircular()
        {
            InitializeComponent();
            this.SetStyle(ControlStyles.UserPaint | ControlStyles.AllPaintingInWmPaint | ControlStyles.OptimizedDoubleBuffer, true);
        }
        protected override void OnPaintBackground(PaintEventArgs e) { }
        protected override void OnPaint(PaintEventArgs e)
        {
            GraphicsPath path = new GraphicsPath();
            path.AddEllipse(0, 0, Width, Height);
            this.Region = new Region(path);

            e.Graphics.SmoothingMode = SmoothingMode.AntiAlias;

            if (BackgroundImage != null)
            {
                e.Graphics.SetClip(path);
                e.Graphics.DrawImage(BackgroundImage, 0, 0, Width, Height);
            }

        }
        
    }
}
