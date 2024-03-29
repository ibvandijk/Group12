-- DROP DATABASE Codecademy
CREATE DATABASE [Codecademy]

GO
USE [Codecademy]

/****** Object:  Table [dbo].[ContentItem]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ContentItem](
	[CourseName] [nvarchar](50) NOT NULL,
	[PublicationDate] [date] NOT NULL,
	[Status] [nvarchar](50) NOT NULL,
	[ContentItemID] [int] NOT NULL,
 CONSTRAINT [PK_ContentItem] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[CourseName] [nvarchar](50) NOT NULL,
	[Subject] [nvarchar](50) NOT NULL,
	[IntroductionText] [text] NULL,
	[Difficulty] [smallint] NOT NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CoursesRecommendedToCourse]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CoursesRecommendedToCourse](
	[MainCourse] [nvarchar](50) NOT NULL,
	[CourseBeingRecommended] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_CoursesRecommendedToCourse] PRIMARY KEY CLUSTERED 
(
	[MainCourse] ASC,
	[CourseBeingRecommended] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Enrollment]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Enrollment](
	[StudentEmail] [varchar](240) NOT NULL,
	[CourseName] [nvarchar](50) NOT NULL,
	[EnrollmentDate] [date] NOT NULL,
 CONSTRAINT [PK_Enrollment] PRIMARY KEY CLUSTERED 
(
	[StudentEmail] ASC,
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Module]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Module](
	[ContentItemID] [int] NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[Version] [int] NOT NULL,
	[Description] [text] NULL,
	[ContactName] [nvarchar](50) NOT NULL,
	[ContactEmail] [varchar](240) NOT NULL,
	[TrackingID] [int] NOT NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[RecommendedCourse]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RecommendedCourse](
	[CourseName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_RecommendedCourse] PRIMARY KEY CLUSTERED 
(
	[CourseName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[Email] [varchar](240) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Birthday] [date] NOT NULL,
	[Sex] [varchar](7) NOT NULL,
	[Adress] [nvarchar](50) NOT NULL,
	[Residence] [nvarchar](50) NULL,
	[Country] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentContentItemProgress]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentContentItemProgress](
	[StudentEmail] [varchar](240) NOT NULL,
	[ContentItemID] [int] NOT NULL,
	[Progress] [float] NOT NULL,
 CONSTRAINT [PK_StudentContentItemProgress] PRIMARY KEY CLUSTERED 
(
	[StudentEmail] ASC,
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Webcast]    Script Date: 07/04/2023 14:14:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Webcast](
	[Title] [nvarchar](50) NOT NULL,
	[ContentItemID] [int] NOT NULL,
	[Description] [text] NULL,
	[Speaker] [nvarchar](50) NOT NULL,
	[Organisation] [nvarchar](50) NULL,
	[ViewCount] [bigint] NOT NULL,
 CONSTRAINT [PK_Webcast] PRIMARY KEY CLUSTERED 
(
	[ContentItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[ContentItem]  WITH CHECK ADD  CONSTRAINT [FK_Course_ContentItem] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[ContentItem] CHECK CONSTRAINT [FK_Course_ContentItem]
GO
ALTER TABLE [dbo].[CoursesRecommendedToCourse]  WITH CHECK ADD  CONSTRAINT [FK_CoursesRecommendedToCourse_Course] FOREIGN KEY([MainCourse])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[CoursesRecommendedToCourse] CHECK CONSTRAINT [FK_CoursesRecommendedToCourse_Course]
GO
ALTER TABLE [dbo].[CoursesRecommendedToCourse]  WITH CHECK ADD  CONSTRAINT [FK_CoursesRecommendedToCourse_RecommendedCourse] FOREIGN KEY([CourseBeingRecommended])
REFERENCES [dbo].[RecommendedCourse] ([CourseName])
GO
ALTER TABLE [dbo].[CoursesRecommendedToCourse] CHECK CONSTRAINT [FK_CoursesRecommendedToCourse_RecommendedCourse]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Course] FOREIGN KEY([CourseName])
REFERENCES [dbo].[Course] ([CourseName])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Course]
GO
ALTER TABLE [dbo].[Enrollment]  WITH CHECK ADD  CONSTRAINT [FK_Enrollment_Student] FOREIGN KEY([StudentEmail])
REFERENCES [dbo].[Student] ([Email])
GO
ALTER TABLE [dbo].[Enrollment] CHECK CONSTRAINT [FK_Enrollment_Student]
GO
ALTER TABLE [dbo].[Module]  WITH CHECK ADD  CONSTRAINT [FK_Module_ContentItem] FOREIGN KEY([ContentItemID])
REFERENCES [dbo].[ContentItem] ([ContentItemID])
GO
ALTER TABLE [dbo].[Module] CHECK CONSTRAINT [FK_Module_ContentItem]
GO
ALTER TABLE [dbo].[StudentContentItemProgress]  WITH CHECK ADD  CONSTRAINT [FK_StudentContentItemProgress_Student] FOREIGN KEY([StudentEmail])
REFERENCES [dbo].[Student] ([Email])
GO
ALTER TABLE [dbo].[StudentContentItemProgress] CHECK CONSTRAINT [FK_StudentContentItemProgress_Student]
GO
ALTER TABLE [dbo].[Webcast]  WITH CHECK ADD  CONSTRAINT [FK_Webcast_ContentItem] FOREIGN KEY([ContentItemID])
REFERENCES [dbo].[ContentItem] ([ContentItemID])
GO
ALTER TABLE [dbo].[Webcast] CHECK CONSTRAINT [FK_Webcast_ContentItem]
GO
