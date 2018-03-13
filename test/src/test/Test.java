package test;


import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Test {
	
	
	public static String type;
	public static String inputFilePath;
	
	public static int numDeclarations;
	public static int numReferences;
	
	
	public static void main(String[] args) 
	{
		//TODO
		getUserInput();
		String source = convertSourceToString();
		
		parseSource(source);
		printResults();
	}
	
	public static void getUserInput()
	{
		//TODO - set type in here as well

	}
	
	public static String convertSourceToString()
	{
		//TODO
		String source = "";
		return source;

	}

	public static void parseSource(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS9);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		
		parser.setUnitName(inputFilePath.toString());
		
		String[] classPath = {""};//source class file location
		String[] sourcePath = {""};//source java file location
		
		parser.setEnvironment(classPath, sourcePath, new String[] {"UTF-8"}, true);
		
		
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor()
			{
			public boolean visit(VariableDeclarationFragment node)
				{

					ITypeBinding binding = node.getName().resolveTypeBinding();
					System.out.println(binding);
					
					return true;
				}
			});
		
		
		return;
	}
	
	public static void printResults()
	{
		//TODO
		return;
	}

}
