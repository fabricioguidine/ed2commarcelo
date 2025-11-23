% Monografia Class File for UFJF
% Based on UFJF Monography Template

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{monografia}[2024/11/23 UFJF Monography Class]

% Pass options to article class
\DeclareOption*{\PassOptionsToClass{\CurrentOption}{report}}
\ProcessOptions\relax

% Load report class as base
\LoadClass[12pt,a4paper]{report}

% Required packages
\RequirePackage[utf8]{inputenc}
\RequirePackage[T1]{fontenc}
\RequirePackage[english,brazil]{babel}
\RequirePackage{setspace}
\RequirePackage{geometry}
\RequirePackage{graphicx}
\RequirePackage{fancyhdr}
\RequirePackage{titlesec}
\RequirePackage{tocloft}

% Page setup
\geometry{
    a4paper,
    left=3cm,
    top=3cm,
    right=2cm,
    bottom=2cm
}

% Spacing
\onehalfspacing

% Title page commands
\newcommand{\@title}{}
\newcommand{\@author}{}
\newcommand{\@local}{Juiz de Fora}
\newcommand{\@date}{\today}
\newcommand{\@institution}{Universidade Federal de Juiz de Fora}
\newcommand{\@course}{}

\newcommand{\title}[1]{\renewcommand{\@title}{#1}}
\newcommand{\author}[1]{\renewcommand{\@author}{#1}}
\newcommand{\local}[1]{\renewcommand{\@local}{#1}}
\newcommand{\date}[1]{\renewcommand{\@date}{#1}}
\newcommand{\institution}[1]{\renewcommand{\@institution}{#1}}
\newcommand{\course}[1]{\renewcommand{\@course}{#1}}

% Title page
\renewcommand{\maketitle}{%
    \begin{titlepage}
        \begin{center}
            \vspace*{2cm}
            
            {\Large\bfseries \@institution\par}
            \vspace{1cm}
            
            {\large \@course\par}
            \vspace{3cm}
            
            {\LARGE\bfseries \@title\par}
            \vspace{2cm}
            
            {\large \@author\par}
            \vspace{3cm}
            
            {\large \@local\par}
            \vspace{1cm}
            
            {\large \@date\par}
        \end{center}
    \end{titlepage}
    \newpage
}

% Resumo environment
\newenvironment{resumo}[1]{%
    \chapter*{#1}
    \addcontentsline{toc}{chapter}{#1}
    \begin{quote}
}{%
    \end{quote}
}

% Dedicatoria environment
\newenvironment{dedicatoria}{%
    \chapter*{}
    \thispagestyle{empty}
    \vspace*{\fill}
    \begin{flushright}
        \begin{minipage}{0.6\textwidth}
            \begin{itshape}
}{%
            \end{itshape}
        \end{minipage}
    \end{flushright}
    \vspace*{\fill}
    \newpage
}

% Agradecimento environment
\newenvironment{agradecimento}[1]{%
    \chapter*{#1}
    \addcontentsline{toc}{chapter}{#1}
    \begin{quote}
}{%
    \end{quote}
    \newpage
}

% Epigrafe environment
\newenvironment{epigrafe}{%
    \chapter*{}
    \thispagestyle{empty}
    \vspace*{\fill}
    \begin{flushright}
        \begin{minipage}{0.6\textwidth}
            \begin{itshape}
}{%
            \end{itshape}
        \end{minipage}
    \end{flushright}
    \vspace*{\fill}
    \newpage
}

% Palavras-chave command
\newcommand{\palavraschave}[1]{%
    \vspace{1cm}
    \noindent\textbf{Palavras-chave:} #1
    \newpage
}

% Keywords command (for English)
\newcommand{\keywords}[1]{%
    \vspace{1cm}
    \noindent\textbf{Keywords:} #1
    \newpage
}

% Header and footer setup
\pagestyle{fancy}
\fancyhf{}
\fancyhead[L]{\small\leftmark}
\fancyhead[R]{\small\thepage}
\fancyfoot{}

% Chapter title formatting
\titleformat{\chapter}[display]
    {\normalfont\huge\bfseries}
    {\chaptertitlename\ \thechapter}{20pt}{\Huge}
\titlespacing*{\chapter}{0pt}{50pt}{40pt}

% Table of contents formatting
\renewcommand{\cftchapfont}{\bfseries}
\setlength{\cftchapnumwidth}{2em}

% List of figures and tables formatting
\renewcommand{\cftfigfont}{\normalfont}
\renewcommand{\cfttabfont}{\normalfont}

% Bibliography command
\newcommand{\bibliographystyle}[1]{%
    \renewcommand{\@bibstyle}{#1}%
}

\newcommand{\bibliography}[1]{%
    \if@filesw
        \immediate\write\@auxout{\string\bibdata{#1}}%
        \immediate\write\@auxout{\string\bibstyle{\@bibstyle}}%
    \fi
    \@input@{\jobname.bbl}%
}

% Default bibliography style
\newcommand{\@bibstyle}{plain}

\endinput

